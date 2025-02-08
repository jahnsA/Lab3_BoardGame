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

        //BoardNode startPosition = boardGame.getToStartNode();
        //loop until one of the arrays has all pawns in home (will get this working later)
        //make a method called checkWin that returns a boolean for this loop
        for (int i = 0; i < 44; i++) {
            boardGame.playerPlays(boardGame.drawCard());
            boardGame.printBoard();
        }

        System.out.println();
    }//end main method
}//end class Main
