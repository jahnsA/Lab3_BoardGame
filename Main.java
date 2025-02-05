


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
