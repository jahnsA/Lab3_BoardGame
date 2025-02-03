


public class Main {
    public static void main(String[] args) {
        //arrays for user and computer pawn positions
        int[] userPawnPos = new int[4];
        int[] compPawnPos = new int[4];
        BoardGame boardGame = new BoardGame();
        boardGame.createBoard();
        
        boardGame.createBoardLinkedList();

        System.out.println();
        boardGame.printoutLinkedList(); //testing linkedlist worked
/*
|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |* *|                     |_|
|_| |* *|                YOU  |_|
|_| COMP                |   | |_|
|_|                     |   | |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|  */ 
    }//end main method
}//end class Main
