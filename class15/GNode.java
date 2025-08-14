package class15;

import java.util.ArrayList;

public class GNode {

    public int value;
    public int in;
    public int out;
    ArrayList<GNode> nexts;
    ArrayList<GEdge> edges;

    public GNode(int v, int i, int o) {
        this.value = v;
        this.in = i;
        this.out = o;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

}
