

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //initialize section
        Scanner scan = new Scanner(System.in);
        BoardGame boardGame = new BoardGame();
        boardGame.createBoardLinkedList();
        boardGame.createSafeZones();
        //arrays for user and computer pawn positions
        int[] userPos = new int[4];
        int[] compPos = new int[4];

        boardGame.stackDeck();

        //within a loop
        //user first
        boardGame.drawCard();
        boardGame.printBoard(userPos, compPos);
        

        System.out.println();
        boardGame.choosePawn(userPos, scan);
    }//end main method
}//end class Main
