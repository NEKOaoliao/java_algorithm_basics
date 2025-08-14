package class16;

import java.util.*;

public class TopologySort {
    
    public static List<GNode> sortTopology(Graph graph) {
        HashMap<GNode, Integer> imap = new HashMap<>();
        Queue<GNode> que = new LinkedList<>();

        for(GNode node: graph.nodes.values()) {
            imap.put(node, node.in);
            if(node.in == 0) {
                que.add(node);
            }
        }
        List<GNode> res = new ArrayList<>();
        while(!que.isEmpty()) {
            GNode node = que.poll();
            for(GNode nextnode: node.nexts) {
                imap.put(nextnode, imap.get(nextnode) - 1);
                if (imap.get(nextnode) == 0) {
                    que.add(nextnode);
                }
            }
            res.add(node);
        }

        return res;
    }
}
