
enum nodePosition {
    spawnPoint, homeSpot, sliderStartSpot, regularSpot
} 
public class BoardNode {
    nodePosition position;
    BoardNode next;
    BoardNode prev;
    
    public BoardNode(nodePosition position) {
        this.position = position;
        next = null; //points to node in front
        prev = null; //points to node behind
    }

    //return what the node position is
    public nodePosition getNodePosition() {
        return this.position;
    }

    //set the node position to another thing
    public void setNodePosition(nodePosition position) {
        this.position = position;
    }

    //for test code for printing linkedlist
    //delete later bc we probably wont need it for the board 
    public String toString() {
        return this.position.name();
    }
}
