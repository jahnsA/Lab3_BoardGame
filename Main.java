


public class Main {
    public static void main(String[] args) {
        //arrays for user and computer pawn positions
        int[] userPawnPos = new int[4];
        int[] compPawnPos = new int[4];
        BoardGame boardGame = new BoardGame();
        boardGame.createBoard();
        
        boardGame.createBoardLinkedList();

        System.out.println();
        //boardGame.printoutLinkedList(); //testing linkedlist worked
/* 
C = computer start pt
Y = your start pt      
|_|_|_|_|C|_|_|_|_|_|_|_|_|_|_|_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |_|                       |_|
|_| |* *|                     |_|
|_| |* *|                     |_|
|_| COMP                |H O| |_|
|_|                     |M E| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|_|_|_|_|_|_|_|_|_|_|_|Y|_|_|_|
                         ^
                          */ 
    }//end main method
}//end class Main
