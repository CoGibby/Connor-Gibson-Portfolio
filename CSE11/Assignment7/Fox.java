///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Models a fox with weekly mass loss, predation behavior, and offspring
 * management
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Fox extends Animal {

    // Constants replacing magic numbers
    private static final double OFFSPRING_LOSS_RATE = 0.05;
    private static final double ADULT_LOSS_RATE = 0.025;
    private static final double ACTION_RADIUS = 1000.0;

    private static final double WEEKLY_MASS_LOSS = 30.0;
    private static final double HEALTH_LOSS = 0.05;

    private static final int MAX_OFFSPRING = 2;
    private static final double MIN_REPRO_HEALTH = 0.5;
    private static final double REPRO_CHANCE = 0.1;

    private static final double BIRTH_MASS_FRACTION = 0.20;

    private static final double LEMMING_SUCCESS_BASE = 0.4;
    private static final double LEMMING_SUCCESS_SCALE = 0.2;

    private static final double CROWBERRY_SUCCESS_BASE = 0.1;
    private static final double CROWBERRY_SUCCESS_SCALE = 0.1;

    private static final double CROWBERRY_MASS_FRACTION = 0.3;
    private static final double CROWBERRY_REMAINING = 0.7;

    private static final double FEED_PARENT_GAIN_NO_OFFSPRING = 0.4;
    private static final double FEED_PARENT_GAIN_WITH_OFFSPRING = 0.2;
    private static final double FEED_OFFSPRING_SHARE = 0.2;

    private static final double HEALTH_GAIN = 0.2;
    private static final double MAX_HEALTH = 1.0;

    private ArrayList<Fox> offspring;

    /**
     * Creates a fox with default values
     */
    public Fox() {
        super();
        offspring = new ArrayList<Fox>();
    }

    /**
     * Creates a fox with the given state and offspring status
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     * @param isOffspring True if this fox is an offspring
     */
    public Fox(double mass, double health, double locX,
               double locY, boolean isOffspring) {
        super(mass, health, locX, locY,
              isOffspring ? OFFSPRING_LOSS_RATE : ADULT_LOSS_RATE,
              ACTION_RADIUS);
        offspring = new ArrayList<Fox>();
    }

    /**
     * Updates the fox's mass, health, and offspring for one week
     *
     * @throws CreatureDeathException Thrown if the fox dies
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {

        double newMass =
            getMass() * (1.0 - getWeeklyLossRate()) - WEEKLY_MASS_LOSS;

        if (newMass <= 0.0) {
            throw new CreatureDeathException("A fox died");
        }

        setMass(newMass);

        double newHealth = getHealth() - HEALTH_LOSS;

        if (newHealth <= 0.0) {
            throw new CreatureDeathException("A fox died");
        }

        setHealth(newHealth);

        for (int i = offspring.size() - 1; i >= 0; i--) {
            try {
                offspring.get(i).advanceWeek();
            } catch (CreatureDeathException e) {
                offspring.remove(i);
            }
        }
    }
    /**
     * Determines whether the fox reproduces this week
     *
     * @return True if reproduction occurs
     */
    @Override
    public boolean triggerReproduce() {

        if (offspring.size() > MAX_OFFSPRING
            || getHealth() < MIN_REPRO_HEALTH) {
            return false;
        }

        double random = Math.random();
        return random <= REPRO_CHANCE;
    }

    /**
     * Creates a new fox offspring and updates the parent's mass
     */
    public void giveBirth() {

        double newFoxMass = getMass() * BIRTH_MASS_FRACTION;

        Fox newFox = new Fox(newFoxMass, getHealth(),
                             getLocX(), getLocY(), true);

        setMass(getMass() - newFoxMass);
        offspring.add(newFox);
    }

    /**
     * Attempts to prey on another creature
     *
     * @param other Creature to attempt to prey on
     * @return True if predation succeeds
     */
    @Override
    public boolean preyOn(Creature other) {

        double successRate = 0.0;

        if (!(other instanceof Lemming)
            && !(other instanceof Crowberry)) {
            return false;
        }

        if (!other.isAlive()) {
            return false;
        }

        if (other instanceof Lemming) {
            successRate =
                LEMMING_SUCCESS_BASE
                + LEMMING_SUCCESS_SCALE * this.compareTo(other);
        } else if (other instanceof Crowberry) {
            successRate =
                CROWBERRY_SUCCESS_BASE
                + CROWBERRY_SUCCESS_SCALE * other.compareTo(this);
        }

        double randomNum = Math.random();
        if (randomNum > successRate) {
            return false;
        }

        feedOn(other);
        return true;
    }

    /**
     * Feeds on another creature and distributes gained mass
     *
     * @param other Creature being fed on
     */
    @Override
    public void feedOn(Creature other) {

        double reducedMass = 0.0;

        if (other instanceof Lemming) {
            reducedMass = other.getMass();
            other.setMass(0.0);
        } else if (other instanceof Crowberry) {
            reducedMass = other.getMass() * CROWBERRY_MASS_FRACTION;
            other.setMass(other.getMass() * CROWBERRY_REMAINING);
            other.setHealth(other.getHealth() * CROWBERRY_REMAINING);
        }

        if (offspring.size() == 0) {
            setMass(getMass()
                + (reducedMass * FEED_PARENT_GAIN_NO_OFFSPRING));
        } else {
            setMass(getMass()
                + (reducedMass * FEED_PARENT_GAIN_WITH_OFFSPRING));

            int offspringLength = offspring.size();
            double average =
                (reducedMass * FEED_OFFSPRING_SHARE) / offspringLength;

            for (int i = 0; i < offspringLength; i++) {
                offspring.get(i).setMass(
                    offspring.get(i).getMass() + average);
            }
        }

        double newHealth = getHealth() + HEALTH_GAIN;
        if (newHealth > MAX_HEALTH) {
            newHealth = MAX_HEALTH;
        }

        setHealth(newHealth);
    }

    /**
     * Adds an offspring fox to this fox's list
     *
     * @param other Offspring fox to add
     */
    public void addOffspring(Fox other) {
        offspring.add(other);
    }

    /**
     * Returns a copy of all offspring
     *
     * @return List of offspring foxes
     */
    public ArrayList<Fox> getAllOffspring() {
        ArrayList<Fox> foxOffspring = new ArrayList<>(offspring);
        return foxOffspring;
    }
}