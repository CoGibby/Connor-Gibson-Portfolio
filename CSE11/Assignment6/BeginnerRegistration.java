///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////
/**
 * The beginner registration which extends registration
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class BeginnerRegistration extends Registration {

    private long trainerId;
    private int totalTrainingTime;
    private int battlesCompleted;
    private static final double TRAINING_TIME_MULTIPLIER = 0.5;
    private static final double BATTLES_COMPLETED_MULTIPLIER = 3.0; 
    private static final double MIN_TRAINING_TIME = 0; 
    private static final double MAX_TRAINING_TIME = 8000;
    private static final double MID_TRAINING_TIME = 800;
    private static final double TRAINING_BATTLES = 15;
    private static final double NUM = 10;


    /**
     * No arg constructor for beginner registration
     * Initializes beginner fields to default values
     */
    public BeginnerRegistration() {
        super();
        trainerId = 0L;
        totalTrainingTime = 0;
        battlesCompleted = 0;
    }

    /**
     * Arg constructor for beginner registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param trainerId The trainer id of the registration
     * @param totalTrainingTime The total training time
     * @param battlesCompleted The number of completed battles
     */
    public BeginnerRegistration(
        String registrationName,
        double experienceLevel,
        long trainerId,
        int totalTrainingTime,
        int battlesCompleted
    ) {
        super(registrationName, experienceLevel);
        this.trainerId = trainerId;
        this.totalTrainingTime = totalTrainingTime;
        this.battlesCompleted = battlesCompleted;
    }

    /**
     * Returns the trainer id
     *
     * @return The trainer id
     */
    public long getTrainerId() {
        return trainerId;
    }

    /**
     * Returns the total training time
     *
     * @return The total training time
     */
    public int getTotalTrainingTime() {
        return totalTrainingTime;
    }

    /**
     * Returns the number of completed battles
     *
     * @return The number of completed battles
     */
    public int getBattlesCompleted() {
        return battlesCompleted;
    }

    /**
     * Computes and returns the power level of this registration
     *
     * @return The computed power value
     */
    @Override
    public double getPower() {
        return getExperienceLevel()
            + (totalTrainingTime * TRAINING_TIME_MULTIPLIER)
            + (battlesCompleted * BATTLES_COMPLETED_MULTIPLIER);
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {

        if (!super.isValidRegistration()) {
            return false;
        }

        if (totalTrainingTime < MIN_TRAINING_TIME || 
            totalTrainingTime > MAX_TRAINING_TIME) {
            return false;
        }

        if (totalTrainingTime > MID_TRAINING_TIME && 
            battlesCompleted < TRAINING_BATTLES) {
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
        if (!(object instanceof BeginnerRegistration)) {
            return false;
        }

        BeginnerRegistration other =
            (BeginnerRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        return trainerId == other.trainerId
            && totalTrainingTime == other.totalTrainingTime
            && battlesCompleted == other.battlesCompleted;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "BeginnerRegistration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", trainerId=" + trainerId
            + ", totalTrainingTime=" + totalTrainingTime
            + ", battlesCompleted=" + battlesCompleted
            + '}';
    }
}
