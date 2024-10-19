package class08;

import java.util.HashMap;

public class TrieTwo {

    class Node {
        public int pass;
        public int end;
        public HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    class Trie {   

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)str[i];
                if(!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public void erase(String word) {
            if(countWordsEqualTo(word) <= 0) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)str[i];
                if(--node.nexts.get(path).pass == 0) {
                    node.nexts.remove(path);
                    return;
                }
                node = node.nexts.get(path);
            }
            node.end--;
        }

        public int countWordsEqualTo(String word) {
            if(word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node node = root;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)str[i];
                if(!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        public int countWordsStartingWith(String word) {
            if(word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node node = root;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)str[i];
                if(!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
        


    }

}
