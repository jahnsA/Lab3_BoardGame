import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class BoardGame {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 45;
    private Stack<Card> Deck;

    BoardNode head; //top of linked list
    BoardNode tail;//end of linked list (points to head)

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references
    //create a stack for deck of cards
    Stack<Card> stackedDeck = new Stack<>();

    // Constructor fills deck of cards and initialized head and tail
    public BoardGame() {
        this.head = null;
        this.tail = null;
        int arrayPos = 0;
        //add the rest of the deck
        for (CardType i: CardType.values()) { //go through each type of card
            //add 5 ones
            if (i == CardType.ONE){
                for (int j = 0; j < 5; j++) {
                    deck[arrayPos] = new Card(i);
                    arrayPos++;
                }//end inner for loop
            } else {
                //add 4 for all other card types
                for (int x = 0; x < 4; x++) {
                    deck[arrayPos] = new Card(i);
                    arrayPos++;
                }//end inner for loop
            }//end if/else
        }//end outer for loop
    }//end DeckOfCards constructor

    //shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        //next call to method dealCard should start at deck[0] again
        for (int first = 0; first < deck.length; first++) {
            //select a random number between 0 and 45
            int second = randomNumbers.nextInt(deck.length);

            //swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }//end for loop
    }//end of shuffle method

    //shuffle array and turn to stack
    public void stackDeck(){
        //sets up game by shuffling deck
        shuffle();
        //add array cards to deck 
        for (int i = 0; i < deck.length; i++) {
            this.Deck.push(deck[i]);
        }//end for loop
    }//end stackDeck

    
    public void createBoardLinkedList() {
        //create node 1
        head = new BoardNode(nodePosition.regularSpot, 1);
        tail = head;//so we can add
        for (int i=2; i<=59; i++){//add nodes 2-59
            insertAtEnd(i);
        }//end for
        //add 60th spot and link to 1st
        tail.next = new BoardNode(nodePosition.regularSpot, 60);
        tail = tail.next;
        tail.next = head;
        head.prev = tail; 
    }//end createBoardLinkedList

    //assumes there's already a head node
    //takes in current position
    public void insertAtEnd(int currentPos){
        BoardNode temp = new BoardNode(nodePosition.regularSpot, currentPos);
        tail.next = temp;
        temp.prev = tail;
        tail = temp;
    }//end insertAtEnd

    static void printList(BoardNode curr, BoardNode head) {

        // return if list is empty
        if (head == null) return;
        
        System.out.print(curr.getSpaceNum() + " ");
        
        if (curr.next == head)
            return;
        
        printList(curr.next, head);

    }
    public void printList(){
        printList(head, head);
    }
    //creates circular double linked list with 60 spaces
    /*public void createBoardLinkedList() {
        //create first node at beginning
        BoardNode firstNode = new BoardNode(nodePosition.regularSpot, 1);
        //set this node to the head
        head = firstNode;
        //make it a circular linked list
        firstNode.next = firstNode;
        firstNode.prev = firstNode;
        //add 58 board nodes to linked list (since 1 is already created)
        for (int i = 0; i < 58; i++){
            addNode(i+1);
        }//end for loop

        //add 60th node and link to first node to make a circle
    }//end createBoardLinkedList method

    //add new node at end
    public void addNode(int currentNum) {
        //knowing that linked list is not empty didn't check for null head
        //create new node
        BoardNode newNode = new BoardNode(nodePosition.regularSpot, currentNum);
        //insert new Node at end
        newNode.prev = head.prev;
        newNode.next = head;
        head.prev.next = newNode;
        head.prev = newNode;
    }//end addNode method*/

    //helper method to call printoutLinkedList(head)
    public void printoutLinkedList(){
        printoutLinkedList(this.head);
    }

    //test code method
    //prints out entire linked list in string with numbers to count spaces
    public void printoutLinkedList(BoardNode head) {
        int count = 1;
        if (head == null) {
            System.out.println("Board Empty");
            return;
        }
        BoardNode current = head;
        do {
            System.out.print(current.toString() + ": " + count);
            current = current.next;
            count++;
            System.out.println();
        } while (current != head);
    }//end of test code method

    //print the current board
    //takes in user and computer pawn position arrays
    public void printBoard(int[] userPos, int[] compPos) {
        //print key
        System.out.println("C = computer start pt, Y = your start pt, # = computer pawn, * = player pawn");
        //print top row (spaces 1-16)
        printRow(1, 16, compPos, userPos);

        //create left and right columns and top safety zone
        int leftColPos = 60;//position in left column
        int rightColPos = 17;//position in right column
        for (int t = 1; t <= 5; t++) {//5 rows
            //print left column (60-56)
            printCell(leftColPos, userPos, compPos);
            leftColPos--; //decrease to move down left column

            System.out.print(" ");//space between left column and safety zone
            System.out.print("|_|");//safety zone
            //print spaces in between columns
            for (int y = 0; y < 23; y++){
                System.out.print(" ");
            }//end for loop

            //right column spaces 17-21
            printCell(rightColPos, userPos, compPos);
            rightColPos++; //increase to move down right column
            System.out.println();//move to new row
        }//end for loop

        //create left and right columns and computer home zone
        leftColPos = 55;
        rightColPos = 22; //starting pt for right column
        for (int i = 1; i <= 2; i++) {//2 rows
            //left column
            printCell(leftColPos, userPos, compPos);
            leftColPos--;
            //end left column

            System.out.print(" ");//space between left column and computer home
            System.out.print("|  ");//left side computer home
            System.out.print(" |");//right side computer home
            for(int k = 0; k < 21; k++){//spaces between computer home and right column
                System.out.print(" ");
            }//end for loop

            //RIGHT COLUMN (22-23)
            printCell(rightColPos, userPos, compPos);
            rightColPos++;
            System.out.println();
            //end right column
        }//end for loop
        
        leftColPos = 53;
        rightColPos = 24;//resetting to new value
        //columns and user home zone
        for (int t = 1; t <= 2; t++) {
            //LEFT COLUMN (53-52)
            printCell(leftColPos, userPos, compPos);
            leftColPos--;
            //END LEFT COLUMN

            //print spaces in between left column and user home zone
            for (int y = 0; y < 21; y++){
                System.out.print(" ");
            }//end for loop
            System.out.print("|  ");//left side home zone
            System.out.print(" |");//right side home zone
            System.out.print(" ");//space between home zone and right column

            //RIGHT COLUMN (24-25)
            printCell(rightColPos, userPos, compPos);
            rightColPos++;
            System.out.println();
        }//end for loop

        //create left and right columns and botttom safety zone
        leftColPos = 51;
        rightColPos = 26;
        for (int t = 0; t < 5; t++) {
            //left column (51-47)
            printCell(leftColPos, userPos, compPos);
            leftColPos--;
            //print spaces in between left column and safety zone
            for (int y = 0; y < 23; y++){
                System.out.print(" ");
            }//end for loop
    
            System.out.print("|_| ");//safety zone

            //right column (26-30)
            printCell(rightColPos, userPos, compPos);
            System.out.println();
            rightColPos++;
        }//end for loop

        //create bottom row (spaces 46-31)
        printRow(46, 31, compPos, userPos);
    }//end createBoard

    //print a cell 
    //takes in current position, user position array, computer position array
    public void printCell(int currPos, int[] userPos, int[]compPos){
        boolean placed = false;//resetting for right side array
        //right column spaces 17-21
        for (int i = 0; i < 4; i++) {//traverse user and comp arrays
            if (userPos[i] == currPos){
                System.out.print("|*|");
                placed = true;
            } else if (compPos[i] == currPos) {
                System.out.print("|#|");
                placed = true;
            }//end if/else
        }//end for
        if(!placed){//if no pawn on spot
            System.out.print("|_|"); 
        }//end if 
    }//end printCell

    //helper method for create board: PRINT ROW
    //takes in leftmost space #, rightmost space #, computer position array, and user position array
    public void printRow (int left, int right, int[]compPos, int[] userPos) {
        for (int i = 1; i <= 16; i++) {//16 spaces across
            boolean placed = false;//resets to placed for each new space
            for(int pawn = 0; pawn < 4; pawn++){ //check computer and user pawn arrays
                if(compPos[pawn] == left) {//if computer pawn on space
                    System.out.print("|#");
                    placed = true;
                } else if (userPos[pawn] == left) {//if user pawn on space
                    System.out.print("|*");
                    placed = true;
                }///end if/else
            }//end for
            //if no pawn on this pt
            if (placed == false){
                if (i == 5 && left < right){//computer start
                    System.out.print("|C");
                } else if (i == 12 && left > right){ //user start
                    System.out.print("|Y");
                } else {//normal spot
                    System.out.print("|_");
                }//end if/else
            }//end outer if
            if(left < right){ //this determines which direction the numbers are printing
                left++;
            } else {
                left--;
            }//end if/else
        }//end for
        System.out.println("|");//close top row and start new line
    }//end printRow

    //method that moves the pawn forward a certain amount of spaces
    public void moveForward(BoardNode currentPositon, int spacesToBeMoved) {
        if (spacesToBeMoved == 1) {
            currentPositon = currentPositon.next;
        } else {
            for(int i = 0; i< spacesToBeMoved-1; i++) {
                currentPositon = currentPositon.next;
            }
        }
        
    }//end moveForward

    //method that moves the pawn back a certain amount of spaces
    public void moveBackward(BoardNode currentPosition, int spacesToBeMoved) {
        if (spacesToBeMoved == 1) {
            currentPosition = currentPosition.prev;
        } else {
            for(int i = 0; i< spacesToBeMoved-1; i++) {
                currentPosition = currentPosition.prev;
            }
        }
    } //end noveBackward

    //print card that prints card and gives player choices
    //NEEDS DRAW CARD METHOD, START PAWN FROM START METHOD, SWITCH PAWN POS OF COMPUTER W/ YOURS METHOD
    //NEED A WAY TO SAVE PLAYERS AND COMPUTERS PAWN POSITIONS
    //ADD CONSTRUCTOR FOR COMPUTER PAWN?
    public void printCard(Card sorryCard, BoardNode currentPosition, Scanner input) {
        //switch statement to print the values of the cards out?
        int playerChoice = 0;
        switch (sorryCard.getCardType()) {
            case ONE: //one card, need player input
                System.out.println("You pulled a ONE!\nEither start a pawn or move one pawn 1 space forward.");
                //give the player the option
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. start a pawn or 2. Move the pawn forward 1?");
                        playerChoice = input.nextInt();
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry. Enter a number: ");
                        input.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) {
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = input.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                
                if (playerChoice == 1) {
                    //start pawn
                    System.out.println(1);
                } else if (playerChoice == 2) {
                    //move pawn forward 1
                    moveForward(currentPosition, 1);
                    System.out.println(2);
                } 
                break;

            case TWO: //two card, needs player input
                System.out.println("You pulled a TWO!\nEither start a pawn or move one pawn 2 spaces forward, and DRAW AGAIN");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. start a pawn or 2. Move the pawn forward 2 spaces?");
                        playerChoice = input.nextInt();
                        
                    } catch (InputMismatchException e) { //errror checking
                        System.out.println("Invalid entry. Enter a number: ");
                        input.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = input.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                if (playerChoice == 1) {
                    //start pawn
                } else if (playerChoice == 2) {
                    //move pawn forward 2
                }
                //ADD DRAW CARD METHOD
                break;

            case THREE: //three card, no player input
                System.out.println("You pulled a THREE!\nMove one pawn 3 spaces forward!");
                //move the pawn forward three spaces
                moveForward(currentPosition, 3);
                break;
            case FOUR: //four card, no player input
                System.out.println("You pulled a FOUR!\nMove one pawn backward 4 spaces!");
                //move the pawn backward four spaces
                moveBackward(currentPosition, 4);
                break;
            case FIVE: //five card, no player input
                System.out.println("You pulled a FIVE!\nMove one pawn 5 spaces forward!");
                //move the pawn forward five spaces
                moveForward(currentPosition, 5);
                break;

            case SEVEN: //seven card, player input needed
                System.out.println("You pulled a SEVEN!\nEither move one pawn forward 7 spaces OR split the move between two pawns!");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move forward 7 spaces or 2. split the move between two pawns?");
                        playerChoice = input.nextInt();
                        
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        input.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = input.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);

                if (playerChoice == 1) {
                    //move forward seven paces
                    moveForward(currentPosition, 7);
                } else if (playerChoice == 2) {
                    //split move between two pawns
                } 
                break;
                
            case EIGHT: //eight card, no player input
                System.out.println("You pulled a EIGHT!\nMove one pawn forward 8 spaces!");
                //move the pawn forward eight spaces
                moveForward(currentPosition, 8);
                break;

            case TEN:
                System.out.println("You pulled a TEN!\nEither move one pawn forward 10 spaces OR move one pawn backward one space!");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move one pawn forward 10 spaces or 2. move one pawn backward one space?");
                        playerChoice = input.nextInt();
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        input.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = input.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                if (playerChoice == 1) {
                    //move forward 10
                    moveForward(currentPosition, 10);
                } else if (playerChoice == 2) {
                    //move backward 1
                    moveBackward(currentPosition, 1);
                } 
                break;

            case ELEVEN:
                System.out.println("You pulled a ELEVEN!\nMove pawn forward 10 spaces OR switch one of your pawns with one of the opponent’s");
                //give player option on what to do
                
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move one pawn forward 10 spaces or 2. switch one of your pawns with one of your oppoenent's?");
                        playerChoice = input.nextInt();
                        
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        input.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = input.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                if (playerChoice == 1) {
                    //move pawn foward 10 spaces
                    moveForward(currentPosition, 10);
                } else if (playerChoice == 2) {
                    //switch pawn with one of opponents   
                }
                break;

            case TWELVE: //twelve card, no player input
                System.out.println("You pulled a TWELVE!\nMove one pawn forward 12 spaces");
                moveForward(currentPosition, 12);
                break;

            case SORRY:
                System.out.println("You pulled a SORRY CARD!\nTake one pawn from start, replace it with one of your oppenent's pawn!");
                 //give player option on what to do

            default:
                break;
        }
    } //end of printCard method

    //player draws a card
    public void drawCard(Stack<Card> sorryDeck){
        

    }

    ///computer play methods
    public void computerPlays(Card sorryCard) {
        switch (sorryCard.getCardType()) {
            case ONE: //move one spot forward or start pawn
                
                break;

            case TWO: //move two spots forward or start pawn
                
                break;

            case THREE: //move three spots forward
                
                break;

            case FOUR: //move backward four
                
                break;

            case FIVE: //move forward five
                
                break;
        
            case SEVEN: //move forward seven or split move between two pawns
                
                break;
            
            case EIGHT: //move forward eight spots
                
                break;
        
            case TEN: //either move forward ten or move backward one 
                
                break;

            case ELEVEN: //either move forward ten or move backward one 
                
                break;
            
            case TWELVE: //move forward 12

                break;

            case SORRY: //switch one pawn with another

                break;
            default:
                break;
        }
    }

    
}//end class BoardGame-nm