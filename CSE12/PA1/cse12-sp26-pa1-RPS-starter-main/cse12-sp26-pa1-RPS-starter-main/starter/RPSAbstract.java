/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file is used to demonstrate what a properly styled file looks like.
   It comes with class and method headers samples that you can use as a
   template for styling your assignment files. (Remember 1-4 sentences)
 */

// If there are imports, they should go here.
import java.util.Random;

/**
 * This class stores two numbers and is able to perform simple arithmetic
 * operations. It can also display its stored numbers. (Remember 1-2 sentences)
 * 
 * Instance variables: (these won't come up until future PAs)
 * firstNum - The first number that is stored by the class
 * secondNum - The second number that is stored by the class
 */
public abstract class RPSAbstract implements RPSInterface {
    protected static final int SEED = 12;
    protected final Random rand = new Random(SEED);

    // The moves allowed in this version of RPS
    protected String[] possibleMoves;

    // The number of games, player wins, CPU wins and ties
    protected int numGames;
    protected int numPlayerWins;
    protected int numCPUWins;
    protected int numTies;

    // The complete history of the moves
    protected String[] playerMoves;
    protected String[] cpuMoves;

    // The history of the last 3 player victories
    protected String[] recentPlayerMoves;
    protected String[] recentCPUMoves;

    // The default moves.  Use for the basic implementation of the game.
    protected static final String[] DEFAULT_MOVES = {"rock", "paper",
            "scissors"};

    /* Important: Use the following constants to avoid output matching issues
       and magic numbers! */

    // Messages for the game.
    protected static final String INVALID_INPUT =
            "That is not a valid move. Please try again.";
    protected static final String PLAYER_WIN = "You win.";
    protected static final String CPU_WIN = "I win.";
    protected static final String TIE = "It's a tie.";
    protected static final String CPU_MOVE = "I chose %s. ";
    protected static final String THANKS =
            "Thanks for playing!\nOur most recent games were:";
    protected static final String PROMPT_MOVE =
            "Let's play! What's your move? (Type the move or q to quit)";
    protected static final String THREE_WINS = 
            "Your last %d victories were:\n";
    protected static final String NO_WINS = 
            "You didn't win any games :(";

    protected static final String OVERALL_STATS =
            "Our overall stats are:\n" +
                    "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    protected static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";


    // Game outcome constants.
    protected static final int CPU_WIN_OUTCOME = 2;
    protected static final int PLAYER_WIN_OUTCOME = 1;
    protected static final int TIE_OUTCOME = 0;
    protected static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.
    protected static final int MAX_GAMES = 100;
    protected static final int MAX_RECENT_WINS = 3;
    protected static final int MIN_POSSIBLE_MOVES = 3;
    protected static final int NUM_ROUNDS_TO_DISPLAY = 10;
    protected static final int PERCENTAGE_RESIZE = 100;
    protected static final String QUIT = "q";
    /**
     * This method checks if a move is valid, 
     * or within the list of possibleMoves
     * 
     * @param move The move being tested to see 
     * if it is valid
     * @return Whether it is valid (true/false)
     */
    @Override
    public boolean isValidMove(String move) {
        // TODO
        // Use a loop here
        for (int i = 0; i < possibleMoves.length; i++){
            if (possibleMoves[i].equals(move)){
                return true;
            }
        }
        return false;  // dummy return value so code compiles.
    }
    /**
     * This method simulates a round of RPS
     * and updates lists accordingly
     * 
     * @param playerMove The move that the player chose
     * @param cpuMove The move that the cpu chose (random)
     */
    @Override
    public void playRPS(String playerMove, String cpuMove) {
        // TODO

        // Use determineWinner to determine who won

        // Record the moves made

        // Add one to the appropriate statistics

        // Add appropriate Javadoc method header
        int winner = determineWinner(playerMove, cpuMove);

        System.out.printf(CPU_MOVE, cpuMove);

        if (winner == PLAYER_WIN_OUTCOME) {
            System.out.println(PLAYER_WIN);
            numPlayerWins++;

            playerMoves[numGames] = playerMove;
            cpuMoves[numGames] = cpuMove;

        } else if (winner == CPU_WIN_OUTCOME) {
            System.out.println(CPU_WIN);
            numCPUWins++;

            playerMoves[numGames] = playerMove;
            cpuMoves[numGames] = cpuMove;

        } else {
            System.out.println(TIE);
            numTies++;

            playerMoves[numGames] = playerMove;
            cpuMoves[numGames] = cpuMove;

        }

        numGames++;
    }
    /**
     * This method will populate up to 3 recent wins
     * 
     */
    @Override
    public void populateRecentWins(){
        // TODO
        // Use a loop here
        
        recentPlayerMoves = new String[MAX_RECENT_WINS];
        recentCPUMoves = new String[MAX_RECENT_WINS];

        int count = 0;

        for (int i = numGames - 1; i >= 0 && count < MAX_RECENT_WINS; i--) {

            int winner = determineWinner(playerMoves[i], cpuMoves[i]);

            if (winner == PLAYER_WIN_OUTCOME) {
                recentPlayerMoves[count] = playerMoves[i];
                recentCPUMoves[count] = cpuMoves[i];
                count++;
            }
        }

    }


    // The following methods have been already implemented. Do not change them.

    /**
     * Generates next cpu move
     *
     * @return representing the move, depending on random int
     */
    @Override
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    @Override
    public void displayStats() {
        float cpuWinPercent = (float) numCPUWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float) numPlayerWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float) numTies /
                (float) numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printToNumRounds = (numGames < NUM_ROUNDS_TO_DISPLAY)
                ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;

        // Print system and user moves
        for (int i = numGames - 1; i >= printToNumRounds; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                    this.playerMoves[i]);
        }

        // Print moves of last 3 player victories
        if (numPlayerWins > 0){
            int printToRecentWins = (numPlayerWins < MAX_RECENT_WINS)
                    ? numPlayerWins : MAX_RECENT_WINS;

            System.out.printf(THREE_WINS, printToRecentWins);
            populateRecentWins();

            for (int i = 0; i < printToRecentWins; i++) {
                System.out.printf(CPU_PLAYER_MOVES, this.recentCPUMoves[i],
                    this.recentPlayerMoves[i]);
            }
        } else {
            System.out.println(NO_WINS);
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
                tiedPercent);
    }
}
