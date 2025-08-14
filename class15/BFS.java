package class15;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    

    public static void bfs(GNode node) {
        if(node == null) {
            return;
        }
        Queue<GNode> que = new LinkedList<>();
        HashSet<GNode> set = new HashSet<>();

        que.add(node);
        set.add(node);
        while(!que.isEmpty()) {
            GNode cur = que.poll();
            System.out.println(node.value);
            for(GNode gn: cur.nexts) {
                if (!set.contains(gn)) {
                    que.add(gn);
                    set.add(gn);
                }
            }
        }
    }
}
