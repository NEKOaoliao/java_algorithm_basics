package class15;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer, GNode> nodes;
    public HashSet<GEdge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
    
}
