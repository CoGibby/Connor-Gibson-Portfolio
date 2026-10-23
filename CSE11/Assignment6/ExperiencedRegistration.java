///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * A more experienced registration that extends registration
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class ExperiencedRegistration extends Registration {

    private long clubId;
    private int numPastTournaments;
    private int numTournamentFouls;

    // Constants to remove magic numbers
    private static final double PAST_TOURNAMENT_MULTIPLIER = 1.5;
    private static final double TOURNAMENT_FOUL_MULTIPLIER = 0.5;
    private static final int MIN_PAST_TOURNAMENTS = 5;
    private static final int MAX_TOURNAMENT_FOULS = 3;

    /**
     * No arg constructor for experienced registration
     * Initializes experienced fields to default values
     */
    public ExperiencedRegistration() {
        super();
        clubId = 0L;
        numPastTournaments = 0;
        numTournamentFouls = 0;
    }

    /**
     * Arg constructor for experienced registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param clubId The club id of the registration
     * @param numPastTournaments The number of past tournaments
     * @param numTournamentFouls The number of tournament fouls
     */
    public ExperiencedRegistration(
        String registrationName,
        double experienceLevel,
        long clubId,
        int numPastTournaments,
        int numTournamentFouls
    ) {
        super(registrationName, experienceLevel);
        this.clubId = clubId;
        this.numPastTournaments = numPastTournaments;
        this.numTournamentFouls = numTournamentFouls;
    }

    /**
     * Returns the club id
     *
     * @return The club id
     */
    public long getClubId() {
        return clubId;
    }

    /**
     * Returns the number of past tournaments
     *
     * @return The number of past tournaments
     */
    public int getNumPastTournaments() {
        return numPastTournaments;
    }

    /**
     * Returns the number of tournament fouls
     *
     * @return The number of tournament fouls
     */
    public int getNumTournamentFouls() {
        return numTournamentFouls;
    }

    /**
     * Computes and returns the power level of this registration
     *
     * @return The computed power value
     */
    @Override
    public double getPower() {
        return getExperienceLevel()
            + (numPastTournaments * PAST_TOURNAMENT_MULTIPLIER)
            - (numTournamentFouls * TOURNAMENT_FOUL_MULTIPLIER);
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {

        if (numPastTournaments < MIN_PAST_TOURNAMENTS) {
            return false;
        }

        if (numTournamentFouls > MAX_TOURNAMENT_FOULS) {
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

        if (!(object instanceof ExperiencedRegistration)) {
            return false;
        }

        ExperiencedRegistration other =
            (ExperiencedRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        return clubId == other.clubId
            && numPastTournaments == other.numPastTournaments
            && numTournamentFouls == other.numTournamentFouls;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "ExperiencedRegistration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", clubId=" + clubId
            + ", numPastTournaments=" + numPastTournaments
            + ", numTournamentFouls=" + numTournamentFouls
            + '}';
    }
}
