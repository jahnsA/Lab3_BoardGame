public class Main {
    public static void main(String[] args) {
        //initialize section
        BoardGame boardGame = new BoardGame();
        boardGame.createBoardLinkedList();
        boardGame.createSafeZones();
        boardGame.stackDeck();

        //print blank board
        boardGame.printBoard();

        //loop game until one of the arrays has all pawns in home
        while(!boardGame.compIsWinner() && !boardGame.userIsWinner()) {
            boardGame.playerPlays(boardGame.drawCard());
            boardGame.printBoard();
        }//end while loop
    }//end main method
}//end class Main
