


public class Main {
    public static void main(String[] args) {
        //arrays for user and computer pawn positions
        int[] userPos = new int[4];
        int[] compPos = new int[4];
        //TEST CODE
        userPos[0] = 6;
        userPos[1] =24;
        compPos[1] = 36;
        compPos[0]= 5;

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
