package class15;


public class GraphGenerator {

    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for(int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer start = matrix[i][1];
            Integer end = matrix[i][2];

            if (!graph.nodes.containsKey(start)) {
                graph.nodes.put(start, new GNode(start, 0, 0));
            }
            if (!graph.nodes.containsKey(end)) {
                graph.nodes.put(end, new GNode(end, 0, 0));
            }
            GNode s = graph.nodes.get(start);
            s.out++;
            GNode e = graph.nodes.get(end);
            e.in++;
            GEdge edge = new GEdge(weight, s, e);
            s.edges.add(edge);
            graph.edges.add(edge);
        }

        return graph;
    }

}

