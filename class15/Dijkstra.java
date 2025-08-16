package class15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

// no negative weight
public class Dijkstra {

    public static class DistPair implements Comparable<DistPair>{
        private GNode node;
        private Integer distance;

        public DistPair(GNode n, Integer dist) {
            this.node = n;
            this.distance = dist;
        }

        public void setNode(GNode n) {
            this.node = n;
        }

        public void setNode(Integer dist) {
            this.distance = dist;
        }

        public GNode getNode() {
            return this.node;
        }

        public Integer getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(DistPair pair) {
            return this.distance - pair.getDistance();
        }
    }
    public static HashMap<GNode, Integer> dijkstra(GNode startGNode) {
        HashMap<GNode, Integer> distMap = new HashMap<>();
        HashSet<GNode> usedNodes = new HashSet<>();
        PriorityQueue<DistPair> pq = new PriorityQueue<>();

        distMap.put(startGNode, 0);
        pq.add(new DistPair(startGNode, 0));
        while(!pq.isEmpty()) {
            GNode chosenNode = pq.poll().getNode();
            if (usedNodes.contains(chosenNode)) continue;
            
            for (GEdge edge: chosenNode.edges) {
                GNode desNode = edge.endNode;
                if (usedNodes.contains(desNode)) continue;

                int chosenNodeDist = distMap.getOrDefault(chosenNode, Integer.MAX_VALUE);
                if (chosenNodeDist == Integer.MAX_VALUE) continue;

                int oldDist = distMap.getOrDefault(desNode, Integer.MAX_VALUE);
                int newDist = chosenNodeDist + edge.weight;

                if (newDist < oldDist) {
                    pq.add(new DistPair(desNode, newDist));
                    distMap.put(desNode, newDist);
                }
            }
            usedNodes.add(chosenNode);
        }
        return distMap;
    }
}
