
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

    

}
