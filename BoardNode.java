
enum nodePosition {
    spawnPoint, homeSpot, sliderStartSpot, regularSpot
} 
public class BoardNode {
    nodePosition position;
    BoardNode next;
    BoardNode prev;
    private int spaceNum;
    
    public BoardNode(nodePosition position, int spaceNum) {
        this.position = position;
        this.spaceNum = spaceNum;
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

    public int getSpaceNum() {
        return this.spaceNum;
    }

    public void setSpaceNUm(int spaceNum) {
        this.spaceNum = spaceNum;
    }

    //for test code for printing linkedlist
    //delete later bc we probably wont need it for the board 
    public String toString() {
        return this.position.name();
    }
}
