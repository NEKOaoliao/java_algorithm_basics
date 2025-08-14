package class15;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    
    public static void dfs(GNode node) {
        HashSet<GNode> set = new HashSet<>();
        set.add(node);
        process(node, set);
    }
    public static void process(GNode node, HashSet<GNode> set) {
        if (node.nexts.size() == 0) {
            return;
        }
        System.out.println(node.value);
        for(GNode gn: node.nexts) {
            if (!set.contains(gn)) {
                set.add(gn);
                process(gn, set);
            }
        }
    }

    // 为什么要在入栈前就要执行处理逻辑（这里是打印）？
    // 因为只能处理一次，如果是出栈的时候处理有可能该节点会被处理多次，
    // 这里是想要模拟递归的DFS，实际上递归的DFS会在处理当前节点的栈的时候进行处理逻辑
    // 栈上永远保存的是当前的走的路径
    public static void stackedDFS(GNode node) {
        if (node == null) {
            return;
        }
        HashSet<GNode> set = new HashSet<>();
        Stack<GNode> stk = new Stack<>();
        stk.add(node);
        System.out.println(node.value);
        set.add(node);
        while(!stk.isEmpty()) {
            GNode cur = stk.pop();
            for(GNode gn: cur.nexts) {
                if (!set.contains(gn)) {
                    stk.push(cur);
                    stk.push(gn);
                    set.add(gn);
                    System.out.println(gn.value);
                    break;  
                }
            }
        }
        
    }
}
