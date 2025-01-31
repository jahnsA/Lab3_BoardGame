


public class Main {
    public static void main(String[] args) {
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
|_| |* *|                     |_|
|_|                     |   | |_|
|_|                     |   | |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|                       |_| |_|
|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|  */ 
    }//end main method
}//end class Main
