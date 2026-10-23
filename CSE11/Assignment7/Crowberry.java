///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Models a crowberry plant with weekly mass growth and capped health
 * recovery
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Crowberry extends Plant {

    // Constants to replace magic numbers
    private static final double GROWTH_RATE = 0.2;
    private static final double MASS_BONUS = 20.0;
    private static final double MAX_MASS = 2000.0;
    private static final double HEALTH_INCREASE = 0.2;
    private static final double MAX_HEALTH = 1.0;

    /**
     * Creates a crowberry with default values
     */
    public Crowberry() {
        super();
    }

    /**
     * Creates a crowberry with the given mass, health, and location
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     */
    public Crowberry(double mass, double health,
                     double locX, double locY) {
        super(mass, health, locX, locY, GROWTH_RATE);
    }

    /**
     * Updates the crowberry's mass and health for one week
     *
     * @throws CreatureDeathException Thrown if the crowberry dies
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {

        double newMass =
            getMass() * (1.0 + getWeeklyGrowthRate()) + MASS_BONUS;

        if (newMass <= 0.0) {
            throw new CreatureDeathException("A crowberry died");
        } else if (newMass > MAX_MASS) {
            newMass = MAX_MASS;
        }

        setMass(newMass);

        double newHealth = getHealth() + HEALTH_INCREASE;
        if (newHealth > MAX_HEALTH) {
            newHealth = MAX_HEALTH;
        }

        setHealth(newHealth);
    }
}