///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Represents a plant with a weekly growth rate and basic creature state
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public abstract class Plant extends Creature {

    private final double weeklyGrowthRate;

    /**
     * Creates a plant with default zero values
     */
    protected Plant() {
        super();
        weeklyGrowthRate = 0.0;
    }

    /**
     * Creates a plant with the given mass, health, location, and growth rate
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     * @param weeklyGrowthRate Weekly mass growth rate
     */
    protected Plant(double mass, double health, double locX,
                    double locY, double weeklyGrowthRate) {
        super(mass, health, locX, locY);
        this.weeklyGrowthRate = weeklyGrowthRate;
    }

    /**
     * Returns the weekly mass growth rate
     *
     * @return Weekly growth rate
     */
    public double getWeeklyGrowthRate() {
        return weeklyGrowthRate;
    }
}