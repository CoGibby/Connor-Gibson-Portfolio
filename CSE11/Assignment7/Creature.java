///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Represents a generic creature with mass, health, and location values
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public abstract class Creature implements Comparable<Creature> {
    private static final int SQUARED = 2;

    private double mass;
    private double health;
    private double locX;
    private double locY;

    /**
     * Creates a creature with default zero values
     */
    protected Creature() {
        mass = 0.0;
        health = 0.0;
        locX = 0.0;
        locY = 0.0;
    }

    /**
     * Creates a creature with the given mass, health, and location
     *
     * @param mass Initial mass
     * @param health Initial health
     * @param locX X coordinate
     * @param locY Y coordinate
     */
    protected Creature(double mass, double health,
                       double locX, double locY) {
        this.mass = mass;
        this.health = health;
        this.locX = locX;
        this.locY = locY;
    }

    /**
     * Returns the creature's mass
     *
     * @return Mass value
     */
    public double getMass() {
        return mass;
    }

    /**
     * Returns the creature's health
     *
     * @return Health value
     */
    public double getHealth() {
        return health;
    }

    /**
     * Returns the creature's X coordinate
     *
     * @return X location
     */
    public double getLocX() {
        return locX;
    }

    /**
     * Returns the creature's Y coordinate
     *
     * @return Y location
     */
    public double getLocY() {
        return locY;
    }

    /**
     * Sets the creature's mass
     *
     * @param mass New mass value
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Sets the creature's health
     *
     * @param health New health value
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Sets the creature's X coordinate
     *
     * @param locX New X location
     */
    public void setLocX(double locX) {
        this.locX = locX;
    }

    /**
     * Sets the creature's Y coordinate
     *
     * @param locY New Y location
     */
    public void setLocY(double locY) {
        this.locY = locY;
    }

    /**
     * Determines if the creature is alive based on mass
     *
     * @return True if mass is positive
     */
    public boolean isAlive() {
        if (mass > 0) {
            return true;
        }
        return false;
    }

    /**
     * Compares this creature to another by health
     *
     * @param other Creature to compare
     * @return 1 if greater, 0 if equal, -1 if less
     */
    public int compareTo(Creature other) {
        double otherHealth = other.getHealth();
        if (health > otherHealth) {
            return 1;
        } else if (health == otherHealth) {
            return 0;
        }
        return -1;
    }

    /**
     * Computes the Euclidean distance to another creature
     *
     * @param other Creature to measure distance to
     * @return Distance between the two creatures
     */
    public double distanceTo(Creature other) {
        double locXOther = other.getLocX();
        double locYOther = other.getLocY();
        return Math.sqrt(Math.pow(locXOther - locX, SQUARED)
            + Math.pow(locYOther - locY, SQUARED));
    }

    /**
     * Determines if two creatures belong to the same species
     *
     * @param other Creature to compare
     * @return True if both share the same class
     */
    public boolean sameSpecies(Creature other) {
        String classOther = other.getClass().getName();
        if (classOther.equals(this.getClass().getName())) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the creature
     *
     * @return String describing the creature
     */
    @Override
    public String toString() {
        return "(" + getClass().getName() + ")" + " mass: " + getMass()
            + "; health: " + getHealth() + "; locX: " + getLocX()
            + "; locY: " + getLocY();
    }

    /**
     * Advances the creature's state by one week
     *
     * @throws CreatureDeathException Thrown if the creature dies
     */
    public abstract void advanceWeek() throws CreatureDeathException;
}