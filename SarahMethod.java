import java.util.LinkedList;

public  class SarahMethod {

        //a slider method
        //where if they hit the spot that has slider spot, then move forward five spaces
    public void slider(BoardNode node, LinkedList<BoardNode> board) {
        //
        if (node.getNodePosition() == nodePosition.sliderStartSpot) {
            //BoardNode temp = head; //temp value to move through the list

            for(int i = 0; i < 4; i++) { //move forward only five spaces
                //move the character forward five steps
                //temp = temp.next;
            }
        }

    }
    public void printCard(Card sorryCard) {
        //switch statement to print the values of the cards out?
        switch (sorryCard.getCardType()) {
            case ONE: //one card
                System.out.println("You pulled a ONE!\n" + 
                "Either start a pawn or move one pawn 1 space forward.");
                break;
            case TWO:
                System.out.println("You pulled a TWO!\n" + 
                "Either start a pawn or move one pawn 2 spaces forward, and DRAW AGAIN");
                break;
            case THREE:
                System.out.println("You pulled a THREE!\n" +
                "Move one pawn 3 spaces forward!");
                break;
            case FOUR:
                System.out.println("You pulled a FOUR!\n" +
                "Move one pawn backward 4 spaces!");
                break;
            case FIVE:
                System.out.println("You pulled a FIVE!\n" +
                "Move one pawn 5 spaces forward!");
            case SEVEN:
                System.out.println("You pulled a SEVEN!\n" + 
                "Either move one pawn forward 7 spaces OR split the move between two pawns!");
                break;
            case EIGHT:
                System.out.println("You pulled a EIGHT!\n" + 
                "move one pawn forward 8 spaces!");
                break;
            case TEN:
                System.out.println("You pulled a TEN!\n" + 
                " either move one pawn forward 10 spaces OR move one pawn backward one space!");
                break;
            case ELEVEN:
                System.out.println("You pulled a ELEVEN!\n" + 
                "Move pawn forward 10 spaces OR switch one of your pawns with one of the opponent’s");
                break;
            case TWELVE:
                System.out.println("You pulled a TWELVE!\n" + 
                "Move one pawn forward 12 spaces");
                break;
            case SORRY:
                System.out.println("You pulled a SORRY CARD!\n" + 
                "Take one pawn from start, replace it with one of yourtake one pawn from start, replace  oppenent's pawn!");
                break;
            default:
                break;
        }
    }

}