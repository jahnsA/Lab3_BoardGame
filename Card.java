//Class purpose: to create card object
// Define enum for CardType
enum CardType {
    ONE, TWO, THREE, FOUR, FIVE, SEVEN, EIGHT, TEN, ELEVEN, TWELVE, SORRY
}

public class Card {
    private CardType cardType; // Enum for card type

    // constructor initializes card's type
    public Card(CardType cardType) {
        this.cardType = cardType; // Initialize face of card
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public void setCardType (CardType newCardType) {
        this.cardType = newCardType;
    }

}//end class Card
