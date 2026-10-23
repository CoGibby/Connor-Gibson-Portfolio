///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Models a willow plant with weekly mass growth and capped health recovery
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Willow extends Plant implements Cloneable {

    // Constants replacing magic numbers
    private static final double GROWTH_RATE = 0.6;
    private static final double MASS_BONUS = 10.0;
    private static final double MAX_MASS = 160.0;

    private static final double HEALTH_INCREASE = 0.6;
    private static final double MAX_HEALTH = 1.0;

    /**
     * Creates a willow with default values
     */
    public Willow() {
        super();
    }

    /**
     * Creates a willow with the given mass, health, and location
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     */
    public Willow(double mass, double health,
                  double locX, double locY) {
        super(mass, health, locX, locY, GROWTH_RATE);
    }

    /**
     * Creates a clone of this willow with identical state
     *
     * @return A new willow with the same values
     * @throws CloneNotSupportedException Required by Cloneable
     */
    @Override
    public Willow clone() throws CloneNotSupportedException {
        return new Willow(getMass(), getHealth(),
                          getLocX(), getLocY());
    }

    /**
     * Updates the willow's mass and health for one week
     *
     * @throws CreatureDeathException Thrown if the willow dies
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {

        double newMass =
            getMass() * (1.0 + getWeeklyGrowthRate()) + MASS_BONUS;

        if (newMass > MAX_MASS) {
            newMass = MAX_MASS;
        } else if (newMass <= 0.0) {
            throw new CreatureDeathException("A willow died");
        }

        setMass(newMass);

        double newHealth = getHealth() + HEALTH_INCREASE;
        if (newHealth > MAX_HEALTH) {
            newHealth = MAX_HEALTH;
        }

        setHealth(newHealth);
    }
}