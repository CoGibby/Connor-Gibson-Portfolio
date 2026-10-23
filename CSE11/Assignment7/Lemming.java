///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Models a lemming with weekly mass loss, reproduction behavior, and
 * limited feeding ability
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Lemming extends Animal implements Cloneable {

    // Constants replacing magic numbers
    private static final double WEEKLY_LOSS_RATE = 0.1;
    private static final double ACTION_RADIUS = 500.0;

    private static final double WEEKLY_MASS_LOSS = 3.0;
    private static final double HEALTH_LOSS = 0.2;

    private static final double MIN_REPRO_HEALTH = 0.5;
    private static final double REPRO_CHANCE = 0.3;

    private static final double CROWBERRY_SUCCESS_BASE = 0.1;
    private static final double CROWBERRY_SUCCESS_SCALE = 0.1;
    private static final double WILLOW_SUCCESS_RATE = 0.3;

    private static final double CROWBERRY_MASS_FRACTION = 0.05;
    private static final double CROWBERRY_REMAINING = 0.95;

    private static final double HEALTH_GAIN = 0.2;
    private static final double MASS_GAIN_FRACTION = 0.25;

    /**
     * Creates a lemming with default values
     */
    public Lemming() {
        super();
    }

    /**
     * Creates a lemming with the given mass, health, and location
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     */
    public Lemming(double mass, double health,
                   double locX, double locY) {
        super(mass, health, locX, locY,
              WEEKLY_LOSS_RATE, ACTION_RADIUS);
    }

    /**
     * Updates the lemming's mass and health for one week
     *
     * @throws CreatureDeathException Thrown if the lemming dies
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {

        double newMass =
            getMass() * (1.0 - getWeeklyLossRate()) - WEEKLY_MASS_LOSS;

        if (newMass <= 0.0) {
            throw new CreatureDeathException("A lemming died");
        }

        setMass(newMass);

        if (getHealth() - HEALTH_LOSS < 0.0) {
            setHealth(0.0);
        } else {
            setHealth(getHealth() - HEALTH_LOSS);
        }
    }

    /**
     * Determines whether the lemming reproduces this week
     *
     * @return True if reproduction occurs
     */
    @Override
    public boolean triggerReproduce() {

        if (getHealth() < MIN_REPRO_HEALTH) {
            return false;
        }

        double randomness = Math.random();
        return randomness <= REPRO_CHANCE;
    }

    /**
     * Creates a clone of this lemming with identical state
     *
     * @return A new lemming with the same values
     * @throws CloneNotSupportedException Required by Cloneable
     */
    @Override
    public Lemming clone() throws CloneNotSupportedException {
        return new Lemming(getMass(), getHealth(),
                           getLocX(), getLocY());
    }

    /**
     * Attempts to prey on a crowberry or willow
     *
     * @param other Creature to attempt to prey on
     * @return True if predation succeeds
     */
    @Override
    public boolean preyOn(Creature other) {

        double successRate = 0.0;
        double randomNumber = Math.random();

        if (other instanceof Crowberry || other instanceof Willow) {

            if (other.isAlive()) {

                if (other instanceof Crowberry) {
                    successRate =
                        CROWBERRY_SUCCESS_BASE
                        + CROWBERRY_SUCCESS_SCALE * other.compareTo(this);
                } else {
                    successRate = WILLOW_SUCCESS_RATE;
                }

                if (randomNumber <= successRate) {
                    feedOn(other);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Feeds on a plant and updates mass and health
     *
     * @param other Creature being fed on
     */
    @Override
    public void feedOn(Creature other) {

        double reducedMass = 0.0;

        if (other instanceof Crowberry) {
            reducedMass = other.getMass() * CROWBERRY_MASS_FRACTION;
            other.setMass(other.getMass() * CROWBERRY_REMAINING);
            other.setHealth(other.getHealth() * CROWBERRY_REMAINING);
        } else if (other instanceof Willow) {
            reducedMass = other.getMass();
            other.setMass(0.0);
        }

        double newHealth = getHealth() + HEALTH_GAIN;
        if (newHealth > 1.0) {
            newHealth = 1.0;
        }
        setHealth(newHealth);

        setMass(getMass() + (reducedMass * MASS_GAIN_FRACTION));
    }
}