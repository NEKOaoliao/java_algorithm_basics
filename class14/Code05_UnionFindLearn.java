package class14;

import java.util.*;

public class Code05_UnionFindLearn {

    public static class UnionSet<V> {
        private HashMap<V, V> parent = new HashMap<>();
        private HashMap<V, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> valList) {
            for (V v : valList) {
                parent.put(v, v);
                sizeMap.put(v, 1);
            }
        }

        public V findFather(V v) {
            Stack<V> stk = new Stack<>();
            V cur = v;
            stk.add(cur);
            while (parent.get(cur) != cur) {
                cur = parent.get(cur);
                stk.add(cur);
            }
            while (!stk.isEmpty()) {
                parent.put(stk.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V x, V y) {
            if (!parent.containsKey(x) || !parent.containsKey(y)) {
                return false;
            }
            return findFather(x) == findFather(y);
        }

        public void union(V x, V y) {
            V xBoss = findFather(x);
            V yBoss = findFather(y);
            if (xBoss != yBoss) {
                int xSetSize = sizeMap.get(xBoss);
                int ySetSize = sizeMap.get(yBoss);
                V big = xSetSize >= ySetSize ? xBoss : yBoss;
                V small = big == xBoss ? yBoss : xBoss;
                parent.put(small, big);
                sizeMap.put(big, xSetSize + ySetSize);
                sizeMap.remove(small);
            }
        }
        public int getSetSize() {
            return sizeMap.size();
        }
    }

    public static void main(String[] args) {
        UnionSet<Integer> uf = new UnionSet<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Initial set size: " + uf.getSetSize()); // 5

        uf.union(1, 2);
        System.out.println("Set size after union(1,2): " + uf.getSetSize()); // 4

        uf.union(3, 4);
        System.out.println("Set size after union(3,4): " + uf.getSetSize()); // 3

        System.out.println("isSameSet(1,2): " + uf.isSameSet(1, 2)); // true
        System.out.println("isSameSet(1,3): " + uf.isSameSet(1, 3)); // false

        uf.union(2, 3);
        System.out.println("Set size after union(2,3): " + uf.getSetSize()); // 2

        System.out.println("isSameSet(1,4): " + uf.isSameSet(1, 4)); // true
        System.out.println("isSameSet(5,1): " + uf.isSameSet(5, 1)); // false
    }
}
