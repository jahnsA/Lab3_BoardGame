import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class BoardGame {
    Scanner scan = new Scanner(System.in);
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 45;
    private Stack<Card> DiscardPile = new Stack<>();

    private BoardNode head; //top of board linked list
    private BoardNode tail;//end of board linked list (points to head)
    private BoardNode playHead; //head of player list
    private BoardNode playTail; //tail of player list
    private BoardNode compHead; //head of computer lsit
    private BoardNode compTail; //tail of computer list

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references
    //create a stack for deck of cards
    private Stack<Card> stackedDeck = new Stack<>();
    //arrays for user and computer pawn positions
    private static int[] userPos = new int[4];
    private static int[] compPos = new int[4];

    //NOTES: Player start stop is board node #35
    //player home spot is board node #35
    //computer start spot is board node #5
    //computer home spot is board node #3

    // Constructor fills deck of cards
    public BoardGame() {
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
            stackedDeck.push(deck[i]);
        }//end for loop
    }//end stackDeck

    //linked list for outer border of board (doesn't include safety zones)
    public void createBoardLinkedList() {
        //create node 1
        head = new BoardNode(1);
        tail = head;//so we can add
        for (int i=2; i<=59; i++){//add nodes 2-59
            insertAtEnd(i);
        }//end for
        //add 60th spot and link to 1st
        tail.next = new BoardNode(60);
        tail = tail.next;
        tail.next = head;
        head.prev = tail; 
    }//end createBoardLinkedList

    //assumes there's already a head node
    //takes in current position
    public void insertAtEnd(int currentPos){
        BoardNode temp = new BoardNode(currentPos);
        tail.next = temp;
        temp.prev = tail;
        tail = temp;
    }//end insertAtEnd

    //print the current board
    public void printBoard() {
        //print key
        System.out.println("C = computer start pt, Y = your start pt, # = computer pawn, * = player pawn");
        //print top row (spaces 1-16)
        //leave a space for left column key
        printHoriztonalKey(1, 16);
        printRow(1, 16);

        //create left and right columns and top safety zone
        int leftColPos = 60;//position in left column
        int rightColPos = 17;//position in right column
        for (int t = 1; t <= 5; t++) {//5 rows
            System.out.print(leftColPos);//key
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
            System.out.print(rightColPos);//key
            rightColPos++; //increase to move down right column
            System.out.println();//move to new row
        }//end for loop

        //create left and right columns and computer home zone
        leftColPos = 55;
        rightColPos = 22; //starting pt for right column
        for (int i = 1; i <= 2; i++) {//2 rows
            //left column
            System.out.print(leftColPos);//key
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
            System.out.print(rightColPos);//key
            rightColPos++;
            System.out.println();
            //end right column
        }//end for loop
        
        leftColPos = 53;
        rightColPos = 24;//resetting to new value
        //columns and user home zone
        for (int t = 1; t <= 2; t++) {
            //LEFT COLUMN (53-52)
            System.out.print(leftColPos);//key
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
            System.out.print(rightColPos);//key
            rightColPos++;
            System.out.println();
        }//end for loop

        //create left and right columns and botttom safety zone
        leftColPos = 51;
        rightColPos = 26;
        for (int t = 0; t < 5; t++) {
            //left column (51-47)
            System.out.print(leftColPos);//key
            printCell(leftColPos, userPos, compPos);
            leftColPos--;
            //print spaces in between left column and safety zone
            for (int y = 0; y < 23; y++){
                System.out.print(" ");
            }//end for loop
    
            System.out.print("|_| ");//safety zone

            //right column (26-30)
            printCell(rightColPos, userPos, compPos);
            System.out.print(rightColPos);//key
            System.out.println();
            rightColPos++;
        }//end for loop

        //create bottom row (spaces 46-31)
        printRow(46, 31);
        //print bottom row key
        printHoriztonalKey(46, 31);
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
    public void printRow (int left, int right) {
        //leave room for left side key
        System.out.print("  ");
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

    //ONLY WORKS FOR TOP ROW
    public void printHoriztonalKey(int left, int right) {
        //leave space for left side key
        System.out.print("  ");
        //top row (1-16)
        if(left < right){
            //first row
            for (int j = left; j<= right; j++){
                if(j<10){
                    System.out.print("  ");
                } else {
                    String stringVer = Integer.toString(j);
                    System.out.print(" " + stringVer.charAt(0));
                }//end if/else
            }//end for
            System.out.println();
            //leave room for left side key
            System.out.print("  ");
            //second row
            for (int i = left; i <= right; i++) {
                if (i<10){//single digit
                    System.out.print(" " + i);
                } else {
                    String stringVer = Integer.toString(i);
                    System.out.print(" " + stringVer.charAt(1));
                }//end if/else
            } //end for
            System.out.println();
        } else {//bottom row
            //first row
            for (int j = left; j>= right; j--){
                if(j<10){
                    System.out.print("  ");
                } else {
                    String stringVer = Integer.toString(j);
                    System.out.print(" " + stringVer.charAt(0));
                }//end if/else
            }//end for
            System.out.println();
            //second row
            //leave room for left side key
            System.out.print("  ");
            for (int i = left; i >= right; i--) {
                if (i<10){//single digit
                    System.out.print(" " + i);
                } else {
                    String stringVer = Integer.toString(i);
                    System.out.print(" " + stringVer.charAt(1));
                }//end if/else
            }
            System.out.println();
        }//end if
    }//end printHorizontalKey

    //checks if can start a pawn
    //takes in array of player pr computer pawn positions
    public static boolean checkIfCanStartPawn (int[] pawnPos) {
        boolean canPlay = false;       
        for(int i = 0; i < pawnPos.length; i++) {//check if there are any pawns that haven't been started
            if(pawnPos[i] == 0) { //
                canPlay =  true; //can play
                break; //move to next for loop if there is a pawn that hasn't been started
            } else {
                canPlay = false; //can't play
            }//end if/else
        }//end for

        //check if there is already a pawn on the start
        for (int j = 0; j < 4; j++) {
            if (userPos[j] == 35 || compPos[j] == 35) { //if there is already a pawn on user start, can't start another
                canPlay = false;
                break; //if there's a pawn on the start, can't start another
            } else {
                canPlay = true;
            }//end if/else
        }//end for loop
        return canPlay;
    } //end checkIfCanStartPawn
    
    //method that moves the pawn forward a certain amount of spaces
    public void moveForward(BoardNode currentPositon, int spacesToBeMoved, boolean isComputer) {
        //checks to see if the pawn will enter safe zone
        
        if ((checkIfInHomeSpot(currentPositon, isComputer) == true)&&(checkIfEnoughSpaces(spacesToBeMoved, currentPositon, isComputer) == true)) {
            //if it's in the home spot
            //MOVE TO START OF SAFE ZONE
            
        } else if(CheckPawnSafeZone(currentPositon, spacesToBeMoved, isComputer) == true) {
            //moved into safe zone
        }  else if (checkIfEnoughSpaces(spacesToBeMoved, currentPositon, isComputer) == true) { //if you have enough valid spaces to play
            if (spacesToBeMoved == 1) {
                currentPositon = currentPositon.next;
            } else {
                for(int i = 0; i< spacesToBeMoved-1; i++) {
                    currentPositon = currentPositon.next;
                }
            }
        } else {System.out.println("Not enough valid spaces! Your turn is skipped!");} //skip turn
    }//end moveForward

    //checks to see if the pawn will enter the safe zone in the middle of their turn
    public boolean CheckPawnSafeZone(BoardNode pawnPos, int spacesToBeMoved, boolean isComputer) {
        if(checkIfEnoughSpaces(spacesToBeMoved, pawnPos, isComputer) == true) {
            //move it to the safe zone
            if (isComputer== true) {
                //computer pawn
                if ((pawnPos.getSpaceNum() == 60) && (spacesToBeMoved > 3)) { //means it enters the safe zone
                    //enter safe zone
                    moveForward(pawnPos, 3, true);
                    return true;
                } else if((pawnPos.getSpaceNum() == 1) && (spacesToBeMoved > 2)) { //means it enters the safe zone
                     //enter safe zone 
                     moveForward(pawnPos, 2, true);
                     //enter safe zone
                     return true;
                } else if((pawnPos.getSpaceNum() == 2) &&(spacesToBeMoved <= 7)) { //means it enters the safe zone
                     //enter safe zone spaces - 1
                    moveForward(pawnPos, 1, true);
                    //enter safe zone
                    return true;
                } else if((pawnPos.getSpaceNum() == 3) && (spacesToBeMoved <=6)) { //means it enters the safe zone
                     //enter safe zone space
                     //then walk forward
                     moveForward(pawnPos, spacesToBeMoved-6, true);
                     return true;
                } else if(pawnPos.getSpaceNum() - 4 + spacesToBeMoved > 60) { ///means it enters the safe zone
                    int spacesTilSafe = ((pawnPos.getSpaceNum() - 4 + spacesToBeMoved) - 60);
                    moveForward(pawnPos, spacesTilSafe, true);
                    //enter safe zone spaces - 6
                    return true;
                } 
            } else { //computer is false, player pawn and play
                if ((23 + pawnPos.getSpaceNum()) - 3 + spacesToBeMoved > 60) {
                    int spacesTilSafe = (pawnPos.getSpaceNum() -3 + spacesToBeMoved) -60;
                    moveForward(pawnPos, spacesTilSafe, isComputer);
                    //enter safe zone  
                    return true;
                } 
            }
        } return false;
        
    } //end of CheckPawnSafeZone

    //method that moves the pawn back a certain amount of spaces
    public void moveBackward(int spacesToBeMoved) {
        //won't need a check because it goes backwards not forwards
            BoardNode currentPos = getToStartNode();
        if (spacesToBeMoved == 1) {
            currentPos= getToStartNode().prev;
        } else {
            for(int i = 0; i< spacesToBeMoved-1; i++) {
                currentPos = getToStartNode().prev;
            }
        }
        
        
    } //end moveBackward

    //checks if the pawn is in the home spot
    public static boolean checkIfInHomeSpot(BoardNode pawnPos, boolean isComputer) {
        //if isComputer is true, means it's the computer's pawn
        //if isComputer is false, mean it's the player's pawn
        if ((pawnPos.getSpaceNum() == 3)&&(isComputer == true)) { //computer home spot is 3
            return true; //returns true if it's on the home spot
        } else if ((pawnPos.getSpaceNum() == 33) &&(isComputer == false)) { //player home spot is 33
            return true;
        } else {
            return false; //returns false if not
        }
    } //end of checkIfInHomeSpot

    // checks to see if the player/computer has enough valid spaces for their turn, returns false if not,which means their turn is skipped
    public static boolean checkIfEnoughSpaces(int spacestoMove,BoardNode pawnPos, boolean isComputer) {
        //checks if there are enough spaces for the pawn to move into or not enough
        if (isComputer== true) {
            //computer pawn
            if ((pawnPos.getSpaceNum() == 60) && (spacestoMove <= 9)) { //special case -#60 is 9 moves from home
                return true;
            } else if((pawnPos.getSpaceNum() == 1) && (spacestoMove <= 8)) { //special case -#1 is 8 moves from home
                return true;
            } else if((pawnPos.getSpaceNum() == 2) &&(spacestoMove <= 7)) { //special case -#1 is 8 moves from home
                return true;
            } else if((pawnPos.getSpaceNum() == 3) && (spacestoMove <=6)) { //special case -#3 is home spot
                return true;
            } else if(pawnPos.getSpaceNum() - 4 + spacestoMove == 66) { //66 bc there are 66 spots on board including safe zones
                return true;
            } else {return false;}
        }
        else { //computer is false, player pawn and play
            if ((23 + pawnPos.getSpaceNum()) - 3 + spacestoMove == 66) {
                return true;
            } else {return false;} //not enough valid spaces
        }
    } //end checkIfCanPlay
    
    //player plays method
    //takes in the card the player drew and the current position of the pawn they want to move
    //doesn't make sense bc they want to choose which pawn to move after they draw a card
    public void playerPlays(Card sorryCard) {
        //switch statement to print the values of the cards out?
        int playerChoice = 0;
        //ASK THE PLAYER WHICH PAWN THEY WANT TO MOVE
        switch (sorryCard.getCardType()) {
            case ONE: //one card, need player scan
                System.out.println("You pulled a ONE!\nEither start a pawn or move one pawn 1 space forward.");
                //give the player the option
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. start a pawn or 2. Move the pawn forward 1?");
                        playerChoice = scan.nextInt();
                        
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        scan.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = scan.nextInt();
                    }
                    break;
                }//end while loop
                System.out.println("You entered: " + playerChoice); //prints out what number you entered
                
                if (playerChoice == 1) {
                    if(checkIfCanStartPawn(userPos) == true) {
                        //then can start pawn
                        startUserPawn(userPos);
                    } else {
                        //can't start a pawn, must move pawn
                        moveForward(getToStartNode(), 1, false);
                        
                    }
                } else if (playerChoice == 2) {
                    //move pawn forward 1
                    moveForward(getToStartNode(), 1, false);
                } 
                break;

            case TWO: //two card, needs player scan
                System.out.println("You pulled a TWO!\nEither start a pawn or move one pawn 2 spaces forward, and DRAW AGAIN");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. start a pawn or 2. Move the pawn forward 2 spaces?");
                        playerChoice = scan.nextInt();
                        
                    } catch (InputMismatchException e) { //errror checking
                        System.out.println("Invalid entry. Enter a number: ");
                        scan.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = scan.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice); //prints out what you entered
                if (playerChoice == 1) {
                    if(checkIfCanStartPawn(userPos) == true) {
                        //then can start pawn
                        startUserPawn(userPos);
                    } else {
                        //can't start a pawn, must move pawn
                        moveForward(getToStartNode(), 2, false);
                        
                    }
                } else if (playerChoice == 2) {
                    //move pawn forward 2
                    moveForward(getToStartNode(), 2, false);
                }
                //ADD DRAW CARD METHOD
                break;

            case THREE: //three card, no player input
                System.out.println("You pulled a THREE!\nMove one pawn 3 spaces forward!");
                //move the pawn forward three spaces
                moveForward(getToStartNode(), 3, false);
                break;
            case FOUR: //four card, no player input
                System.out.println("You pulled a FOUR!\nMove one pawn backward 4 spaces!");
                //move the pawn backward four spaces
                moveBackward( 4);
                break;
            case FIVE: //five card, no player input
                System.out.println("You pulled a FIVE!\nMove one pawn 5 spaces forward!");
                //move the pawn forward five spaces
                moveForward(getToStartNode(), 5, false);
                break;

            case SEVEN: //seven card, player input needed
                System.out.println("You pulled a SEVEN!\nEither move one pawn forward 7 spaces OR split the move between two pawns!");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move forward 7 spaces or 2. split the move between two pawns?");
                        playerChoice = scan.nextInt();
                        
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        scan.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = scan.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);

                if (playerChoice == 1) {
                    //move forward seven paces
                    moveForward(getToStartNode(), 7, false);
                } else if (playerChoice == 2) {
                    //split move between two pawns
                    while(true) {
                        try {
                            System.out.println("Enter the number of spaces you wish your pawn to move: ");
                            playerChoice = scan.nextInt();
                            
                        } catch (InputMismatchException e) { //error checking
                            System.out.println("Invalid entry. Enter a number: ");
                            scan.next();
                        }
                        if((playerChoice < 0)||(playerChoice >= 7 )) { //error checking,
                            System.out.println("Invalid option. Enter a number between 0-7: ");
                            playerChoice = scan.nextInt();
                        }
                        break;
                    }
                    moveForward(getToStartNode(), playerChoice, false); //first pawn
                    //moveForward(getToStartNode(), 7-playerChoice, false); //second pawn, diff node -> FIX
                } 
                break;
                
            case EIGHT: //eight card, no player input
                System.out.println("You pulled a EIGHT!\nMove one pawn forward 8 spaces!");
                //move the pawn forward eight spaces
                moveForward(getToStartNode(), 8, false);
                break;

            case TEN:
                System.out.println("You pulled a TEN!\nEither move one pawn forward 10 spaces OR move one pawn backward one space!");
                //give player option on what to do
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move one pawn forward 10 spaces or 2. move one pawn backward one space?");
                        playerChoice = scan.nextInt();
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        scan.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = scan.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                if (playerChoice == 1) {
                    //move forward 10
                    moveForward(getToStartNode(), 10, false);
                } else if (playerChoice == 2) {
                    //move backward 1
                    moveBackward( 1);
                } 
                break;

            case ELEVEN:
                System.out.println("You pulled a ELEVEN!\nMove pawn forward 10 spaces OR switch one of your pawns with one of the opponent’s");
                //give player option on what to do
                
                while(true) {
                    try {
                        System.out.println("Do you wish to 1. move one pawn forward 10 spaces or 2. switch one of your pawns with one of your oppoenent's?");
                        playerChoice = scan.nextInt();
                        
                    } catch (InputMismatchException e) { //error checking
                        System.out.println("Invalid entry. Enter a number: ");
                        scan.next();
                    }
                    if((playerChoice != 1)&&(playerChoice != 2)) { //error checking
                        System.out.println("Invalid option. Enter either a 1 or a 2: ");
                        playerChoice = scan.nextInt();
                    }
                    break;
                }
                System.out.println("You entered: " + playerChoice);
                if (playerChoice == 1) {
                    //move pawn foward 10 spaces
                    moveForward(getToStartNode(), 10, false);
                } else if (playerChoice == 2) {
                    //switch pawn with one of opponents   
                }
                break;

            case TWELVE: //twelve card, no player input
                System.out.println("You pulled a TWELVE!\nMove one pawn forward 12 spaces");
                moveForward(getToStartNode(), 12, false);
                break;

            case SORRY:
                System.out.println("You pulled a SORRY CARD!\nTake one pawn from start, replace it with one of your oppenent's pawn!");
                 //give player option on what to do

            default:
                break;
        }
    } //end of printCard method

    public void computerPlays(Card sorryCard, int[] comPawn, BoardNode comPos) {
        //iterate through the array and if it is not in start, chose that one to move around the board
        int pawnNum = 0;
        for(int i = 0; i< comPawn.length-1; i++) {
            if (comPawn[i] != 0) {//if not zero, then it has been already started
                pawnNum = i;
                break;
            }
        }
        //how to get a boardnode from an index? FIX
        for (int i = 0; i < comPawn[pawnNum];i++) { //should iterate through the linked list the number of spaces the pawn is in
            head = head.next;
        }
        comPos = head;
       
        switch (sorryCard.getCardType()) {
            case ONE: //move one spot forward or start pawn
                if(checkIfCanStartPawn(comPawn) == true) {//can start a pawn
                    startUserPawn(comPawn);
                } else {//move forward 1
                    moveForward(comPos, 1, true);
                }   
                break;
        
            case TWO: //move two spots forward or start pawn
                if(checkIfCanStartPawn(comPawn) == true) {
                    //then can start pawn
                    startUserPawn(comPawn);
                } else {
                    //can't start a pawn, must move pawn
                    moveForward(comPos, 2, true);
                }
                break;
        
            case THREE: //move three spots forward
                //move the pawn forward three spaces
                moveForward(comPos, 3, true);
                break;
        
            case FOUR: //move backward four
                //move the pawn backward four spaces
                moveBackward(comPos, 4);
                break;
        
            case FIVE: //move forward five
                 //move the pawn forward five spaces
                moveForward(comPos, 5, true);
                break;
                
            case SEVEN: //move forward seven or split move between two pawns
                //if the pawn can move forward seven then go for seven
                if(checkIfEnoughSpaces(7, comPos, true) == true) {
                    moveForward(comPos, 7, true);
                } else {
                    moveForward(comPos, 3, true);
                    //moveForward(comPos, 4, true); FIX
                }
                break;
            case EIGHT: //move forward eight spots
                moveForward(comPos, 8, true);
                break;
                
            case TEN: //either move forward ten or move backward one 
                //check if they can move foward ten spaces
                //if so, then move ten
                //if not, then move backward one
                if (checkIfEnoughSpaces(10, comPos, true) == true) {
                    moveForward(comPos, 10, false);
                } else {
                    moveBackward(comPos, 1);
                }break;
        
            case ELEVEN: //either move forward ten or switch one of your pawns with another
                //check if they can move forward ten spaces
                //if so, move ten
                //if not, move backward one
                if (checkIfEnoughSpaces(10, comPos, false) == true) {
                    moveForward(comPos, 10, false);
                }
                break;
                    
            case TWELVE: //move forward 12
                //move pawn forward 12 spaces
                moveForward(comPos, 12, true);
                break;
        
            case SORRY: //switch one pawn with another
                //switch computer pawn with opponent pawn
                break;
            default:
                break;
                }
    }

    //start user pawn, takes in user pawn postion array
    //need to make sure it gets to right spot in linked list though
    public void startUserPawn(int[] userPos){
        for (int i = 0; i < 4; i++) {//traverse userPos array
            if(userPos[i] == 0) {//if pawn hasn't been started
            //add case for if there is a pawn (user or computer) on user start (in different method?)
                userPos[i] = 35;
                break;//break so you only place one pawn
            }//end if
        }//end for
    }//end startUserPawn

    //user chooses which pawn they would like to move or stay
    //returns index of pawn user has chosen to play
    public int choosePawn(){
        System.out.println("Which pawn would you like to play? (Enter an integer)");
        System.out.println("0) Don't move");
        for (int i = 0; i < 4; i++) {//traverse user array
            if (userPos[i] != 0){
                System.out.println(i+1 + ") Pawn at spot " + userPos[i]);
            }
        }//end for
        int choice = scan.nextInt();
        return choice - 1;
    }//end choosePawn

    public void createSafeZones() {
        //create player safe zone
        playHead = new BoardNode(61); //creates node 61
        playTail = playHead;
        for(int i = 62; i < 66; i++) { //creates nodes 62-65
            BoardNode playTemp = new BoardNode(i);
            playTail.next = playTemp;
            playTemp.prev = playTail;
            playTail = playTemp;
        }
        playTail.next = new BoardNode(66); //create nodes 66
        playTail = playTail.next;

        //create computer safe zone
        compHead = new BoardNode(67); //create node 67
        compTail = compHead;
        for(int j = 68; j < 73; j++) { //creates nodes 68-72
            BoardNode compTemp = new BoardNode(j);
            compTail.next = compTemp;
            compTemp.prev = compTail;
            compTail = compTemp;
        }
        compTail.next = new BoardNode(73); //create tail
        compTail = compTail.next;

    } //end of create safe zone

    public Card drawCard(){
        DiscardPile.push(stackedDeck.pop());
        return DiscardPile.peek();

    } //end of draw Card

    //takes in int value of space player starts their turn at and translates to board node
    //generalize to work for computer too?
    public BoardNode getToStartNode(){
        BoardNode temp = head;
        for (int i = 1; i <= userPos[choosePawn()]; i++){//think this works?
            temp = temp.next;
        }
        return temp;//the board node that corresponds to the current position
    }//end getToCurrentNode

    
}//end class BoardGame-nm