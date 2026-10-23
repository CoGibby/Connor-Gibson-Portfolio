///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * The inital registration for pokemon
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Registration {

    private String registrationName;
    private double experienceLevel;
    private static final double EPSILON = 1e-9;

    /**
     * No arg constructor for registration
     * Initializes fields to default values
     */
    public Registration() {
        registrationName = "";
        experienceLevel = 0.0;
    }

    /**
     * Arg constructor for registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     */
    public Registration(
        String registrationName,
        double experienceLevel
    ) {
        this.registrationName = registrationName;
        this.experienceLevel = experienceLevel;
    }

    /**
     * Returns the registration name
     *
     * @return The registration name
     */
    public String getRegistrationName() {
        return registrationName;
    }

    /**
     * Returns the experience level
     *
     * @return The experience level
     */
    public double getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * Sets the experience level
     *
     * @param experienceLevel The new experience level
     */
    public void setExperienceLevel(double experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    /**
     * Returns the power value of this registration
     *
     * @return The power value
     */
    public double getPower() {
        return experienceLevel;
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    public boolean isValidRegistration() {
        if (experienceLevel < 0) {
            return false;
        }
        return true;
    }

    /**
     * Determines whether this registration equals another object
     *
     * @param object The object to compare with this registration
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Registration)) {
            return false;
        }
        if (this == object) {
            return true;
        }

        Registration other = (Registration) object;

        boolean namesEqual =
            registrationName.equals(other.registrationName);

        boolean experienceEqual =
            Math.abs(experienceLevel - other.experienceLevel)
            < EPSILON;

        return namesEqual && experienceEqual;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "Registration{"
            + "registrationName=" + registrationName
            + ", experienceLevel=" + experienceLevel
            + '}';
    }
}