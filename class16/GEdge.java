package class16;

public class GEdge {

    public int weight;
    public GNode startNode;
    public GNode endNode;

    public GEdge(int w, GNode s, GNode e) {
        this.weight = w;
        this.startNode = s;
        this.endNode = e;
    }
}
