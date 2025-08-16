package class15;
import java.util.*;

public class DijkstraTester {
    // 测试用例 1：基本功能验证（含多条路径）
    public static void testBasic() {
        // 创建节点
        GNode A = new GNode(0, 0, 0);
        GNode B = new GNode(1, 0, 0);
        GNode C = new GNode(2, 0, 0);
        GNode D = new GNode(3, 0, 0);
        
        // 构建图结构
        /*
          A ---2---> B ---1---> D
          |          |          ^
          |          |          |
          5          6          3
          ↓          ↓          |
          C ---------+----------+
        */
        addEdge(A, B, 2);
        addEdge(A, C, 5);
        addEdge(B, C, 6);
        addEdge(B, D, 1);
        addEdge(C, D, 3);
        
        // 运行算法
        HashMap<GNode, Integer> result = Dijkstra.dijkstra(A);
        
        // 验证结果
        System.out.println("\n=== Test Case 1: Basic ===");
        printResult(result);
        assert result.get(A) == 0 : "A should be 0";
        assert result.get(B) == 2 : "B should be 2";
        assert result.get(C) == 5 : "C should be 5";
        assert result.get(D) == 3 : "D should be 3 (A->B->D)";  // 不是 A->C->D(8)
    }
    
    private static void addEdge(GNode from, GNode to, int weight) {
        GEdge edge = new GEdge(weight, from, to);
        from.edges.add(edge);
        from.nexts.add(to);
        from.out++;
        to.in++;
    }

        // 测试用例 2：验证重复节点处理（多次更新路径）
    public static void testDuplicateHandling() {
        // 创建节点
        GNode start = new GNode(0, 0, 0);
        GNode A = new GNode(1, 0, 0);
        GNode B = new GNode(2, 0, 0);
        GNode C = new GNode(3, 0, 0);
        
        // 构建图结构
        /*
          start ---1---> A ---5---> C
          |             ↑          ^
          |             |          |
          7             2          1
          ↓             |          |
          B ------------+----------+
        */
        addEdge(start, A, 1);
        addEdge(start, B, 7);
        addEdge(A, C, 5);
        addEdge(B, A, 2);
        addEdge(B, C, 1);
        
        // 运行算法
        HashMap<GNode, Integer> result = Dijkstra.dijkstra(start);
        
        // 验证结果
        System.out.println("\n=== Test Case 2: Duplicate Handling ===");
        printResult(result);
        assert result.get(start) == 0 : "Start should be 0";
        assert result.get(A) == 1 : "A should be 1 (direct)";
        assert result.get(B) == 7 : "B should be 7 (direct)";
        assert result.get(C) == 4 : "C should be 4 (A→B→C) not A→C(6) or start→B→C(8)";
    }
    
    private static void printResult(HashMap<GNode, Integer> result) {
        for (GNode node : result.keySet()) {
            System.out.println("Node " + node.value + ": " + result.get(node));
        }
    }

        // 测试用例 3：测试大权重和溢出保护
    public static void testOverflowProtection() {
        // 创建节点
        GNode A = new GNode(0, 0, 0);
        GNode B = new GNode(1, 0, 0);
        GNode C = new GNode(2, 0, 0);
        GNode D = new GNode(3, 0, 0);
        
        /*
        拓扑结构:
        A --MAX--> B --10--> D
        |          |
        |          1
        |          v
        +--------> C
        */
        addEdge(A, B, Integer.MAX_VALUE);
        addEdge(B, D, 10);
        addEdge(B, C, 1);
        addEdge(A, C, 1000);
        
        // 运行算法
        HashMap<GNode, Integer> result = Dijkstra.dijkstra(A);
        
        // 验证结果
        System.out.println("\n=== Test Case 3: Overflow Protection ===");
        printResult(result);
        assert result.get(A) == 0 : "A should be 0";
        assert result.get(B) == null : "B should be unreachable";
        assert result.get(C) == 1000 : "C should be 1000 (direct path)";
        assert result.get(D) == null : "D should be unreachable (through B)";
    }
    
    public static void main(String[] args) {
        testBasic();
        testDuplicateHandling();
        testOverflowProtection();
        System.out.println("\nAll tests passed!");
    }
}