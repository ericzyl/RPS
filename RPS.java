/**
 * Name: Yilin Zhu
 * ID: A15862322
 * Email: yiz095@ucsd.edu
 * Sources used: textbook, java documentation
 * File description: PA1 part 4. It extends RPSAbstract.
 */

import java.util.Scanner;

/**
 * RPS class includes a RPS constructor that assigns initial values.
 * It also includes a determineWinner method to decide the winner.
 * And the main method is where the game executes.
 */
public class RPS extends RPSAbstract {
    
    /**
     * The constructor initializes possible moves, player's move,
     * and the computer's move.
     * @param moves - moves that set to possible moves
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        // index of player's move and cpu's move in possibleMoves array
        // record the difference between player's and cpu's index
        int playerIndex;
        int cpuIndex;
        int difference;

        if(isValidMove(playerMove) && isValidMove(cpuMove)){
            playerIndex = java.util.Arrays.asList(possibleMoves).indexOf(playerMove);
            cpuIndex = java.util.Arrays.asList(possibleMoves).indexOf(cpuMove);
            difference = playerIndex - cpuIndex;

            if(difference == -1 || difference == possibleMoves.length-1){
                return PLAYER_WIN_OUTCOME;
            }
            else if(difference == 1 || difference == 0 - possibleMoves.length + 1){
                return CPU_WIN_OUTCOME;
            }
            else{
                return TIE_OUTCOME;
            }
        }
        else{
            return INVALID_INPUT_OUTCOME;
        }
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        System.out.println(PROMPT_MOVE);

        // check if user input is invalid or equal to 'q', and run the game
        while(in.hasNext()){ 
            String input = in.next();
            if(input.equals(QUIT)){
                break;
            }
            else if(!game.isValidMove(input)){
                System.out.println(INVALID_INPUT);
                System.out.println(PROMPT_MOVE);
            }
            else{
                game.play(input, game.genCPUMove());
                System.out.println(PROMPT_MOVE);
            }
        }
        
        game.end();
        
        in.close();
    }
}
