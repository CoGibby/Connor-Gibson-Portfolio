///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Represents an animal with weekly mass loss and an action radius for
 * interacting with other creatures
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public abstract class Animal extends Creature {

    private final double weeklyLossRate;
    private final double actionRadius;

    /**
     * Creates an animal with default zero values
     */
    protected Animal() {
        super();
        weeklyLossRate = 0.0;
        actionRadius = 0.0;
    }

    /**
     * Creates an animal with the given mass, health, location, loss rate,
     * and action radius
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     * @param weeklyLossRate Weekly mass loss rate
     * @param actionRadius Radius for interacting with other creatures
     */
    protected Animal(double mass, double health, double locX,
                     double locY, double weeklyLossRate,
                     double actionRadius) {
        super(mass, health, locX, locY);
        this.weeklyLossRate = weeklyLossRate;
        this.actionRadius = actionRadius;
    }

    /**
     * Returns the weekly mass loss rate
     *
     * @return Weekly loss rate
     */
    public double getWeeklyLossRate() {
        return weeklyLossRate;
    }

    /**
     * Returns the radius in which the animal can act on other creatures
     *
     * @return Action radius
     */
    public double getActionRadius() {
        return actionRadius;
    }

    /**
     * Determines whether the animal reproduces this week
     *
     * @return True if reproduction occurs
     */
    public abstract boolean triggerReproduce();

    /**
     * Attempts to prey on another creature
     *
     * @param other Creature to attempt to prey on
     * @return True if predation succeeds
     */
    public abstract boolean preyOn(Creature other);

    /**
     * Feeds on another creature and updates mass and health
     *
     * @param other Creature being fed on
     */
    public abstract void feedOn(Creature other);
}