///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * A registration that is extending beginner registration
 * which uses recreational techniques
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class RecreationalRegistration extends BeginnerRegistration {

    private int pokemonInteractions;
    private double friendliness;
    private static final double EPSILON = 1e-9;
    private static final double TRAINING_TIME_MULTIPLIER = 0.3;
    private static final double FRIENDLINESS_MULTIPLIER = 1.2;
    private static final double INTERACTION_MULTIPLIER = 0.5;
    private static final int MIN_INTERACTIONS = 15;
    private static final int MIN_FRIENDLINESS = 50;

    /**
     * No arg constructor for recreational registration
     * Initializes recreational fields to default values
     */
    public RecreationalRegistration() {
        super();
        pokemonInteractions = 0;
        friendliness = 0.0;
    }

    /**
     * Arg constructor for recreational registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param trainerId The trainer id of the registration
     * @param totalTrainingTime The total training time
     * @param battlesCompleted The number of completed battles
     * @param pokemonInteractions The number of pokemon interactions
     * @param friendliness The friendliness score
     */
    public RecreationalRegistration(
        String registrationName,
        double experienceLevel,
        long trainerId,
        int totalTrainingTime,
        int battlesCompleted,
        int pokemonInteractions,
        double friendliness
    ) {
        super(registrationName, experienceLevel, trainerId,
              totalTrainingTime, battlesCompleted);
        this.pokemonInteractions = pokemonInteractions;
        this.friendliness = friendliness;
    }

    /**
     * Returns the number of pokemon interactions
     *
     * @return The number of pokemon interactions
     */
    public int getPokemonInteractions() {
        return pokemonInteractions;
    }

    /**
     * Returns the friendliness score
     *
     * @return The friendliness score
     */
    public double getFriendliness() {
        return friendliness;
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
            + (friendliness * FRIENDLINESS_MULTIPLIER)
            + (pokemonInteractions * INTERACTION_MULTIPLIER);
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {

        if (pokemonInteractions < MIN_INTERACTIONS) {
            return false;
        }

        if (friendliness < MIN_FRIENDLINESS) {
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
        if (!(object instanceof RecreationalRegistration)) {
            return false;
        }
        if (this == object) {
            return true;
        }

        RecreationalRegistration other =
            (RecreationalRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        boolean pokemonInteractionsEqual =
            pokemonInteractions == other.pokemonInteractions;

        boolean friendlinessEqual =
            Math.abs(friendliness - other.friendliness) < EPSILON;

        return friendlinessEqual && pokemonInteractionsEqual;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "RecreationalRegistration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", trainerId=" + getTrainerId()
            + ", totalTrainingTime=" + getTotalTrainingTime()
            + ", battlesCompleted=" + getBattlesCompleted()
            + ", pokemonInteractions=" + pokemonInteractions
            + ", friendliness=" + friendliness
            + '}';
    }
}