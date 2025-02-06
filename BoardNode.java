
 public class BoardNode {
    BoardNode next;
    BoardNode prev;
    private int spaceNum;
    
    public BoardNode(int spaceNum) {
        this.spaceNum = spaceNum;
        this.next = null; //points to node in front
        this.prev = null; //points to node behind
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
        return Integer.toString(this.getSpaceNum());
    }
}
