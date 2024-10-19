//前缀树
package class08;

public class TrieOne {

    class Trie {
        
        class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }

        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if(word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)(str[i] - 'a');
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }
        
        public void erase(String word) {
            if(word == null || countWordsEqualTo(word) < 1) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for(int i = 0; i < str.length; i++) {
                path = (int)(str[i] - 'a');
                if((--node.nexts[path].pass) == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
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
                path = (int)(str[i] - 'a');
                if(node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
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
            for(int i = 0; i < word.length(); i++) {
                path = (int)(str[i] - 'a');
                if(node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }
    }

}
