

import java.util.Scanner;
import javax.management.DescriptorKey;
import jdk.jshell.DeclarationSnippet;
public class Main {
    public static void main(String[] args) {
        //initialize section
        Scanner scan = new Scanner(System.in);
        BoardGame boardGame = new BoardGame();
        boardGame.createBoardLinkedList();
        boardGame.createSafeZones();

        boardGame.stackDeck();

        //within a loop
        //user first
        boardGame.printBoard();
        

        System.out.println();
    }//end main method
}//end class Main
