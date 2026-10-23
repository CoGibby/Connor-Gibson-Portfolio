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
import java.util.Scanner;
/**
 * This class is used to simulate a rock, paper, 
 * scissors match. Extends an abstract class 
 * with more methods to help simulation.
 * 
 * Instance variables: (these won't come up until future PAs)
 * firstNum - The first number that is stored by the class
 * secondNum - The second number that is stored by the class
 */
public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
        this.recentPlayerMoves = new String[MAX_RECENT_WINS];
        this.recentCPUMoves = new String[MAX_RECENT_WINS];
    }

    /**
     * The main method for the structure of the game in the
     * terminal, calling other methods throughout
     * 
     * @param args The default argument into the main method
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game

        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.
        System.out.println(PROMPT_MOVE);
        String playerMove = in.nextLine();
        while (!playerMove.equals(QUIT)){
            String cpuMove = game.genCPUMove();
            if (game.isValidMove(playerMove)){
                game.playRPS(playerMove, cpuMove);
            } else {
                System.out.println(INVALID_INPUT);
            }

            System.out.println(PROMPT_MOVE);
            playerMove = in.nextLine();
        }

        
        game.displayStats();


        in.close();
    }
    /**
     * This method determines whether the player or CPU wins
     * based on their moves. (Also could tie)
     * 
     * @param playerMove The move the player chose (scanner)
     * @param cpuMove A random move thte CPU chooses in
     * a set list of possible moves
     * @return An integer representing the outcome of the match
    */
    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        if (playerMove == null) {
            return INVALID_INPUT_OUTCOME;
        }

        boolean valid = false;
        for (int i = 0; i < possibleMoves.length; i++){
            if (possibleMoves[i].equals(playerMove)){
                valid = true;
            }
        }
        if (!valid) {
            return INVALID_INPUT_OUTCOME;
        }

        int playerMoveIdx = -1;
        int cpuMoveIdx = -1;
        
        for (int j = 0; j < possibleMoves.length; j++){
            if (possibleMoves[j].equals(playerMove)){
                playerMoveIdx = j;
            }
            if (possibleMoves[j].equals(cpuMove) && cpuMove != null){
                cpuMoveIdx = j;
            }
        }
        if (cpuMoveIdx == -1) {
            return INVALID_INPUT_OUTCOME;
        }

        int length = possibleMoves.length;
        if (playerMoveIdx == (cpuMoveIdx + 1) % length) {
            return PLAYER_WIN_OUTCOME;
        }

        if (cpuMoveIdx == (playerMoveIdx + 1) % length) {
            return CPU_WIN_OUTCOME;
        }

        return TIE_OUTCOME;
    }
}
