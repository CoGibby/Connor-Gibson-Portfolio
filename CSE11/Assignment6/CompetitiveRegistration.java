///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * A more advanced registration extending beginner registration
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class CompetitiveRegistration extends BeginnerRegistration {

    private int gymBadges;
    private int battlesWon;
    private int pokemonLevel;

    private static final double TRAINING_TIME_MULTIPLIER = 0.4;
    private static final double GYM_BADGE_MULTIPLIER = 2.5;
    private static final double BATTLES_WON_MULTIPLIER = 1.2;
    private static final double POKEMON_LEVEL_MULTIPLIER = 0.8;
    private static final int MIN_GYM_BADGES = 5;
    private static final int MIN_BATTLES_WON = 20;
    private static final int MIN_POKEMON_LEVEL = 10;

    /**
     * No arg constructor for competitive registration
     * Initializes competitive fields to default values
     */
    public CompetitiveRegistration() {
        super();
        gymBadges = 0;
        battlesWon = 0;
        pokemonLevel = 0;
    }

    /**
     * Arg constructor for competitive registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param trainerId The trainer id of the registration
     * @param totalTrainingTime The total training time
     * @param battlesCompleted The number of completed battles
     * @param gymBadges The number of gym badges earned
     * @param battlesWon The number of battles won
     * @param pokemonLevel The level of the pokemon
     */
    public CompetitiveRegistration(
        String registrationName,
        double experienceLevel,
        long trainerId,
        int totalTrainingTime,
        int battlesCompleted,
        int gymBadges,
        int battlesWon,
        int pokemonLevel
    ) {
        super(registrationName, experienceLevel, trainerId,
              totalTrainingTime, battlesCompleted);
        this.gymBadges = gymBadges;
        this.battlesWon = battlesWon;
        this.pokemonLevel = pokemonLevel;
    }

    /**
     * Returns the number of gym badges
     *
     * @return The number of gym badges
     */
    public int getGymBadges() {
        return gymBadges;
    }

    /**
     * Returns the number of battles won
     *
     * @return The number of battles won
     */
    public int getBattlesWon() {
        return battlesWon;
    }

    /**
     * Returns the pokemon level
     *
     * @return The pokemon level
     */
    public int getPokemonLevel() {
        return pokemonLevel;
    }

    /**
     * Computes and returns the power level of this registration
     *
     * @return The computed power value
     */
    @Override
    public double getPower() {
        return getExperienceLevel()
            + (getTotalTrainingTime() * TRAINING_TIME_MULTIPLIER)
            + (gymBadges * GYM_BADGE_MULTIPLIER)
            + (battlesWon * BATTLES_WON_MULTIPLIER)
            + (pokemonLevel * POKEMON_LEVEL_MULTIPLIER);
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {

        if (gymBadges < MIN_GYM_BADGES) {
            return false;
        }

        if (battlesWon < MIN_BATTLES_WON) {
            return false;
        }

        if (pokemonLevel < MIN_POKEMON_LEVEL) {
            return false;
        }

        return super.isValidRegistration();
    }


    /**
     * Determines whether this registration equals another object
     *
     * @param object The object to compare with this registration
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof CompetitiveRegistration)) {
            return false;
        }

        CompetitiveRegistration other =
            (CompetitiveRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        return gymBadges == other.gymBadges
            && battlesWon == other.battlesWon
            && pokemonLevel == other.pokemonLevel;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "CompetitiveReigstration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", trainerId=" + getTrainerId()
            + ", totalTrainingTime=" + getTotalTrainingTime()
            + ", battlesCompleted=" + getBattlesCompleted()
            + ", gymBadges=" + gymBadges
            + ", battlesWon=" + battlesWon
            + ", pokemonLevel=" + pokemonLevel
            + '}';
    }
}