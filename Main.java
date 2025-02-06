public class Main {
    public static void main(String[] args) {
        //arrays for user and computer pawn positions
        int[] userPos = new int[4];
        int[] compPos = new int[4];
        //TEST CODE
        compPos[0] =24;
        compPos[1] =29;
        userPos[0] = 52;

        //END TEST CODE

        BoardGame boardGame = new BoardGame();
        boardGame.printBoard(userPos, compPos);
        
        boardGame.createBoardLinkedList();

        System.out.println();
        boardGame.printList();
        //boardGame.printoutLinkedList(); //testing linkedlist worked
    }//end main method
}//end class Main
