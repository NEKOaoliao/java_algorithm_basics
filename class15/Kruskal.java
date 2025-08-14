package class15;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Kruskal {
    
    public static class UnionFind {

        // Map node to its father node
        private HashMap<GNode, GNode> fatherHashMap;
        // Map symbol node to the its set size
        // Also can be used to get the subset size through get the hashmap size(symbol node size == subset size)
        private HashMap<GNode, Integer> sizeHashMap;

        public UnionFind() {
            this.fatherHashMap = new HashMap<>();
            this.sizeHashMap = new HashMap<>();
        }

        public void makeSets(Collection<GNode> nodes) {
            fatherHashMap.clear();
            sizeHashMap.clear();
            for(GNode node: nodes) {
                fatherHashMap.put(node, node);
                sizeHashMap.put(node, 1);
            }
        }

        public GNode findFather(GNode n) {
            Stack<GNode> stk = new Stack<>();
            GNode cur = n;
            while(this.fatherHashMap.get(cur) != cur) {
                stk.add(cur);
                cur = this.fatherHashMap.get(cur);
            }
            while(!stk.isEmpty()) {
                GNode node = stk.pop();
                this.fatherHashMap.put(node, cur);
            }

            return cur;
        }

        public boolean isSameSet(GNode n1, GNode n2) {
            return findFather(n1) == findFather(n2);
        }

        public void union(GNode n1, GNode n2) {
            GNode n1father = this.findFather(n1);
            GNode n2father = this.findFather(n2);
            if (!isSameSet(n1, n2)) {
                int n1Size = this.sizeHashMap.get(n1father);
                int n2Size = this.sizeHashMap.get(n2father);

                if(n1Size < n2Size) {
                    this.fatherHashMap.put(n1father, n2father);
                    this.sizeHashMap.remove(n1father);
                } else {
                    this.fatherHashMap.put(n2father, n1father);
                    this.sizeHashMap.remove(n2father);
                }
                this.sizeHashMap.put(n2father, n1Size + n2Size);
            }
        }

        public int getSetSize() {
            return this.sizeHashMap.size();
        }
        
        public static class EdgeComparator implements Comparator<GEdge> {
            
            @Override
            public int compare(GEdge e1, GEdge e2) {
                return e1.weight - e2.weight;
            }

        } 
        public static List<GEdge> kruskalMST(Graph G) {

            UnionFind uf = new UnionFind();
            uf.makeSets(G.nodes.values());

            PriorityQueue<GEdge> pq = new PriorityQueue<>(new EdgeComparator());
            for(GEdge e: G.edges) {
                pq.add(e);
            }

            List<GEdge> results = new LinkedList<>();
            
            while(!pq.isEmpty()) {
                GEdge cur = pq.poll();
                if (!uf.isSameSet(cur.startNode, cur.endNode)) {
                    results.add(cur);
                    uf.union(cur.startNode, cur.endNode);
                }
                if (uf.getSetSize() == 1) {
                    break;
                }
            }

            return results;
        }
    }
}
