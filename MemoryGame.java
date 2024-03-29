import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MemoryGame{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);


        String[] tiles={"A","B","C","D","E","F","G","H","A","B","C","D","E","F","G","H"};
        shuffleTiles(tiles);


        String[] board = new String[tiles.length];
        Arrays.fill(board,"-");


        displayBoard(board);

        try{
         Thread.sleep(3000);   
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    clearConsole();

    playGame(scanner, tiles, board);

    scanner.close();
    }

    private static void shuffleTiles(String[] tiles){
        Collections.shuffle(Arrays.asList(tiles));
    }
    private static void displayBoard(String[] board) {
        System.out.println("Memory Game Board: ");
        for(int i = 0; i<board.length; i++){
            System.out.print(board[i] + " ");
            if((i+1)% 4 == 0){
                System.out.println();
            }
        }
         System.out.println();
    }
    private static void clearConsole() {
         System.out.print("\033[H\033[2J");
          System.out.flush();
    }
    private static void playGame(Scanner scanner, String[] tiles, String[] board) {
        int attempts = 0;

        do{

            displayBoard(board);


             System.out.print("Enter the first position (0-15):");
             int position1 = scanner.nextInt();
             System.out.print("Enter the second position (0-15):");
             int position2 = scanner.nextInt();


            if(isValidPosition(position1, board) && isValidPosition(position2, board) && position1 != position2) {

                board[position1] = tiles[position1];
                board[position2] = tiles[position2];

                displayBoard(board);


                if(tiles[position1].equals(tiles[position2])) {
                    System.out.println("Match Found!!");
                }
                else{
                   System.out.println("No Match. Try Again.");

                   try{
                    Thread.sleep(2000);
                   } 
                   catch(InterruptedException e) {
                    e.printStackTrace();
                   } 
                   board[position1] = "-";
                   board[position2] = "-";
                }

                attempts++;
            }
            else{
                System.out.println("Invalid positions. Please try Again.");
            }

        } 
        while(!isGameOver(board));

        System.out.println("Congratulations! You completed the game in" + attempts + "attempts.");
    }

    private static boolean isValidPosition(int position, String[] board) {
        return position >= 0 && position < board.length && board[position].equals("-");
    }

    private static boolean isGameOver(String[] board) {
        return Arrays.stream(board).noneMatch(tile -> tile.equals("-"));
    }
}