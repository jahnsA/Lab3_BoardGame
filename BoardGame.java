import java.security.SecureRandom;
import java.util.Stack;

public class BoardGame {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 45;
    private Stack<Card> Deck;

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references
    //create a stack for deck of cards
    Stack<Card> stackedDeck = new Stack<>();

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
            this.Deck.push(deck[i]);
        }//end for loop
    }//end stackDeck

    //create a blank board
    public void createBoard(){
        //create top horizontal row
        for (int i = 0; i < 16; i++){//16 spaces across
            System.out.print("|_");
        }//end for loop
        System.out.println("|");//close top row and start new line

        //create left and right columns and top safety zone
        for (int t = 0; t < 6; t++) { //print a column with 7 spaces
            System.out.print("|_| |_|");
            //print spaces in between columns
            for (int y = 0; y < 23; y++){
                System.out.print(" ");
            }//end for loop
            //print safety zone and right column
            System.out.println("|_|");
        }//end for loop

        //create left and right columns (no safety zone)
        for (int i = 0; i < 2; i++) {//print a column with 2 spaces
            System.out.print("|_|");
            //print spaces between columns
            for(int k = 0; k < 27; k++){
                System.out.print(" ");
            }//end for loop
            //print right column
            System.out.println("|_|");
        }//end for loop

        //create left and right columns (botttom safety zone)
        for (int t = 0; t < 6; t++) { //print a column with 7 spaces
            System.out.print("|_|");
            //print spaces in between columns
            for (int y = 0; y < 23; y++){
                System.out.print(" ");
            }//end for loop
            //print safety zone and right column
            System.out.println("|_| |_|");
        }//end for loop

        //create bottom horizontal row
        for (int i = 0; i < 16; i++){//16 spaces across
            System.out.print("|_");
        }//end for loop
    }//end createBoard
}//end class BoardGame