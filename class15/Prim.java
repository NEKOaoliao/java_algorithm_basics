package class15;

import java.util.*;

public class Prim {
    
    public static class CustomEdgeComparator implements Comparator<GEdge> {
        @Override
        public int compare(GEdge e1, GEdge e2) {
            return e1.weight - e2.weight;
        }
    }

    public List<GEdge> primMST(Graph g) {
        List<GEdge> res = new ArrayList<>();
        if (g.nodes.size() <= 0) {
            return res;
        }
        PriorityQueue<GEdge> pq = new PriorityQueue<>(new CustomEdgeComparator());
        Set<GNode> usedNodes = new HashSet<>();
        Set<GEdge> usedEdge = new HashSet<>();
        GNode head = g.nodes.get(0);
        usedNodes.add(head);
        for (GEdge edge: head.edges) {
            pq.add(edge);
        }
        while(!pq.isEmpty()) {
            GEdge se = pq.poll();
            if (usedNodes.contains(se.endNode)) {
                usedEdge.add(se);
                continue;
            }

            res.add(se);

            usedNodes.add(se.endNode);
            for (GEdge edge: se.endNode.edges) {
                if (!usedEdge.contains(edge)) {
                    pq.add(edge);
                }
            }
        }
        return res;
    }

}
