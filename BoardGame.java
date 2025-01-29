import java.security.SecureRandom;
//import javax.smartcardio.Card;
import java.util.Stack;

public class BoardGame {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 45;

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references

    //shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        //next call to method dealCard should start at deck[0] again
        for (int first = 0; first < deck.length; first++) {
            //select a random number between 0 and 51
            int second = randomNumbers.nextInt(deck.length);

            //swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }//end for loop
    }//end of shuffle method

        //shuffle array and turn to stack
    public void stackDeck(Stack<Card> stackedDeck){
        //shuffle deck until last card in array is not an 8 (so that first card
        //dealt into discard pile will not be 8)
        do { 
            shuffle();
        } while(deck[deck.length - 1].getFace() == Face.EIGHT);
        //add array cards to deck 
        for (int i = 0; i < deck.length; i++) {
            stackedDeck.push(deck[i]);
        }//end for loop
    }//end stackDeck

}//end class BoardGame
