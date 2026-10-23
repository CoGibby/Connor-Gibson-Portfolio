///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * This is registering your pokemon to a league, 
 * extending expereinced registration 
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class LeagueRegistration extends ExperiencedRegistration {

    private String leagueName;
    private int leagueRank;

    // Constants to remove magic numbers
    private static final double PAST_TOURNAMENT_MULTIPLIER = 1.5;
    private static final int MIN_LEAGUE_RANK = 1;
    private static final int MAX_LEAGUE_RANK = 200;

    /**
     * No arg constructor for league registration
     * Initializes league fields to default values
     */
    public LeagueRegistration() {
        super();
        leagueName = "";
        leagueRank = MAX_LEAGUE_RANK;
    }

    /**
     * Arg constructor for league registration
     *
     * @param registrationName The name of the registration
     * @param experienceLevel The experience level of the registration
     * @param clubId The club id of the registration
     * @param numPastTournaments The number of past tournaments
     * @param numTournamentFouls The number of tournament fouls
     * @param leagueName The name of the league
     * @param leagueRank The rank within the league
     */
    public LeagueRegistration(
        String registrationName,
        double experienceLevel,
        long clubId,
        int numPastTournaments,
        int numTournamentFouls,
        String leagueName,
        int leagueRank
    ) {
        super(registrationName, experienceLevel, clubId,
              numPastTournaments, numTournamentFouls);
        this.leagueName = leagueName;
        this.leagueRank = leagueRank;
    }

    /**
     * Returns the league name
     *
     * @return The league name
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Returns the league rank
     *
     * @return The league rank
     */
    public int getLeagueRank() {
        return leagueRank;
    }

    /**
     * Computes and returns the power level of this registration
     *
     * @return The computed power value
     */
    @Override
    public double getPower() {
        return getExperienceLevel()
            + ((getNumPastTournaments() * PAST_TOURNAMENT_MULTIPLIER)
            - getNumTournamentFouls()) / leagueRank;
    }

    /**
     * Determines whether this registration is valid
     *
     * @return true if the registration is valid, false otherwise
     */
    @Override
    public boolean isValidRegistration() {
        if (leagueRank < MIN_LEAGUE_RANK
            || leagueRank > MAX_LEAGUE_RANK) {
            return false;
        }
        if (super.isValidRegistration()) {
            return true;
        }
        return false;
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

        if (!(object instanceof LeagueRegistration)) {
            return false;
        }

        LeagueRegistration other =
            (LeagueRegistration) object;

        if (!super.equals(other)) {
            return false;
        }

        return leagueRank == other.leagueRank
            && leagueName.equals(other.leagueName);
    }

    /**
     * Returns a string representation of this registration
     *
     * @return A string describing this registration
     */
    @Override
    public String toString() {
        return "LeagueRegistration{"
            + "registrationName=" + getRegistrationName()
            + ", experienceLevel=" + getExperienceLevel()
            + ", clubId=" + getClubId()
            + ", numPastTournaments=" + getNumPastTournaments()
            + ", numTournamentFouls=" + getNumTournamentFouls()
            + ", leagueName='" + leagueName + '\''
            + ", leagueRank=" + leagueRank
            + '}';
    }
}
