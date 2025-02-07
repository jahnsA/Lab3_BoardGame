public class Main {
    public static void main(String[] args) {
        //initialize section
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
