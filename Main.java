

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BoardGame boardGame = new BoardGame();
        boardGame.createBoardLinkedList();
        Card currentCard;
        //arrays for user and computer pawn positions
        int[] userPos = new int[4];
        int[] compPos = new int[4];

        //test
        userPos[0] = 16;
        userPos[1] = 30;

        //Game loop (user only for now)
        //current card for user (reset each time)
        //boardGame.stackDeck();
        //currentCard = boardGame.stackedDeck.pop();//draw a card
        //boardGame.startUserPawn(userPos);

        boardGame.printBoard(userPos, compPos);
        //System.out.println("User array");
        /*for (int i = 0; i < 4; i++) {
            System.out.println(userPos[i]);
        }*/
        

        System.out.println();

        boardGame.choosePawn(userPos, scan);
        //boardGame.printList();
        //boardGame.printoutLinkedList(); //testing linkedlist worked
    }//end main method
}//end class Main
