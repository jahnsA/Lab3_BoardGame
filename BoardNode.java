
enum nodePosition {
    spawnPoint, homeSpot, sliderStartSpot, regularSpot
} 
public class BoardNode {
    nodePosition position;
    BoardNode next;
    BoardNode prev;
    
    public BoardNode(nodePosition position) {
        //data = x;
        this.position = position;
        next = null; //points to node in front
        prev = null; //points to node behind
    }

    public void setNodePosition(nodePosition position) {
        this.position = position;
    }

}
