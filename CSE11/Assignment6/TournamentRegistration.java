///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Registering your pokemon for a tournament, 
 * extending experienced registration
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class TournamentRegistration extends ExperiencedRegistration {

    private int finalsAppearances;
    private int crowdApproval;
    private double winRate;
    private static final double EPSILON = 1e-9;

    // Constants to remove magic numbers
    private static final double FINALS_MULTIPLIER = 1.5;
    private static final double FOUL_MULTIPLIER = 0.5;
    private static final double CROWD_APPROVAL_MULTIPLIER = 0.3;
    private static final double WIN_RATE_MULTIPLIER = 10.0;
    private static final int MIN_CROWD_APPROVAL = 7;
    private static final int MAX_FINALS_APPEARANCES = 150;
    private static final double MIN_WIN_RATE = 0.6;

    /**
     * No arg constructor for tournament registration
     * Initializes tournament specific fields to default values
     */
    public TournamentRegistration() {
        super();
        finalsAppearances = 0;
        crowdApproval = 0;
        winRate = 0.0;
    }

    /**
     * Arg constructor for tournament registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param clubId The club id of the registration
     * @param numPastTournaments The number of past tournaments
     * @param numTournamentFouls The number of tournament fouls
     * @param finalsAppearances The number of finals appearances
     * @param crowdApproval The crowd approval rating
     * @param winRate The win rate of the registration
     */
    public TournamentRegistration(
        String registrationName,
        double experienceLevel,
        long clubId,
        int numPastTournaments,
        int numTournamentFouls,
        int finalsAppearances,
        int crowdApproval,
        double winRate
    ) {
        super(registrationName, experienceLevel, clubId,
              numPastTournaments, numTournamentFouls);
        this.finalsAppearances = finalsAppearances;
        this.crowdApproval = crowdApproval;
        this.winRate = winRate;
    }

    /**
     * Returns the number of finals appearances
     *
     * @return The number of finals appearances
     */
    public int getFinalsAppearances() {
        return finalsAppearances;
    }

    /**
     * Returns the crowd approval rating
     *
     * @return The crowd approval rating
     */
    public int getCrowdApproval() {
        return crowdApproval;
    }

    /**
     * Returns the win rate
     *
     * @return The win rate
     */
    public double getWinRate() {
        return winRate;
    }

    /**
     * Computes and returns the power level of this registration
     *
     * @return The computed power value
     */
    @Override
    public double getPower() {
        return getExperienceLevel()
            + ((finalsAppearances * FINALS_MULTIPLIER)
            / (getNumPastTournaments()
            + (getNumTournamentFouls() * FOUL_MULTIPLIER)))
            + (crowdApproval * CROWD_APPROVAL_MULTIPLIER)
            + (winRate * WIN_RATE_MULTIPLIER);
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {

        if (finalsAppearances < 0
            || finalsAppearances > MAX_FINALS_APPEARANCES) {
            return false;
        }

        if (crowdApproval < MIN_CROWD_APPROVAL) {
            return false;
        }

        if (winRate < MIN_WIN_RATE) {
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

        if (!(object instanceof TournamentRegistration)) {
            return false;
        }

        TournamentRegistration other =
            (TournamentRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        boolean winRateBoolean =
            Math.abs(winRate - other.winRate) < EPSILON;
        boolean crowdApprovalBoolean =
            crowdApproval == other.crowdApproval;
        boolean finalsAppearancesBoolean =
            finalsAppearances == other.finalsAppearances;

        return crowdApprovalBoolean
            && finalsAppearancesBoolean
            && winRateBoolean;
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "TournamentRegistration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", clubId=" + getClubId()
            + ", numPastTournaments=" + getNumPastTournaments()
            + ", numTournamentFouls=" + getNumTournamentFouls()
            + ", finalsAppearances=" + finalsAppearances
            + ", crowdApproval=" + crowdApproval
            + ", winRate=" + winRate
            + '}';
    }
}