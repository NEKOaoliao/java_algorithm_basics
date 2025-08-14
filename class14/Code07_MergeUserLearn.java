package class14;

import java.util.*;

public class Code07_MergeUserLearn {
    
    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public class UnionSet {
        private HashMap<User, User> parent;
        private HashMap<User, Integer> setSize;

        public UnionSet(List<User> userList) {
            this.parent = new HashMap<>();
            this.setSize = new HashMap<>();

            for (User u : userList) {
                parent.put(u, u);
                setSize.put(u, 1);
            }
        }

        // User u must be contained in the parent map
        public User findFather(User u) {
            Stack<User> stk = new Stack<>();
            User cur = u;
            stk.add(cur);
            User parentUser = parent.get(cur);
            while(parentUser != cur) {
                cur = parentUser;
                stk.add(cur);
                parentUser = parent.get(cur);
            }

            while(!stk.empty()) {
                parent.put(stk.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(User u0, User u1) {
            return findFather(u0) == findFather(u1);
        }

        public void union(User u0, User u1) {

            if (!parent.containsKey(u0) || !parent.containsKey(u1)) {
                return;
            }

            User parentU0 = findFather(u0);
            User parentU1 = findFather(u1);

            int sizeU0 = setSize.get(parentU0);
            int sizeU1 = setSize.get(parentU1);

            User big = sizeU0 >= sizeU1 ? parentU0 : parentU1;
            User small = big == parentU0 ? parentU1 : parentU0;
            
            // union core logic
            parent.put(small, big);
            setSize.put(big, sizeU0 + sizeU1);
            setSize.remove(small);

            return;
        }

        public int sets() {
            return setSize.size();
        }
    }

    public int mergeUser(List<User> userList) {
        if (userList.size() <= 1) {
            return userList.size();
        }
        UnionSet uset = new UnionSet(userList);
        HashMap<String, User> aMap = new HashMap<>();
        HashMap<String, User> bMap = new HashMap<>();
        HashMap<String, User> cMap = new HashMap<>();

        for(User u: userList) {
            if (aMap.get(u.a) != null) {
                uset.union(u, aMap.get(u.a));
            }
            aMap.put(u.a, u);
            if (bMap.get(u.b) != null) {
                uset.union(u, bMap.get(u.b));
            }
            bMap.put(u.b, u);
            if (cMap.get(u.c) != null) {
                uset.union(u, cMap.get(u.c));
            }
            cMap.put(u.c, u);
        }

        return uset.sets();
    }

    
    public static void main(String[] args) {
        Code07_MergeUserLearn merger = new Code07_MergeUserLearn();

        // Test 1: No users
        List<Code07_MergeUserLearn.User> users1 = new ArrayList<>();
        System.out.println("Test 1: " + (merger.mergeUser(users1) == 0));

        // Test 2: Single user
        List<Code07_MergeUserLearn.User> users2 = Arrays.asList(
            new Code07_MergeUserLearn.User("a1", "b1", "c1")
        );
        System.out.println("Test 2: " + (merger.mergeUser(users2) == 1));

        // Test 3: Two users, no merge
        List<Code07_MergeUserLearn.User> users3 = Arrays.asList(
            new Code07_MergeUserLearn.User("a1", "b1", "c1"),
            new Code07_MergeUserLearn.User("a2", "b2", "c2")
        );
        System.out.println("Test 3: " + (merger.mergeUser(users3) == 2));

        // Test 4: Two users, merge by 'a'
        List<Code07_MergeUserLearn.User> users4 = Arrays.asList(
            new Code07_MergeUserLearn.User("a1", "b1", "c1"),
            new Code07_MergeUserLearn.User("a1", "b2", "c2")
        );
        System.out.println("Test 4: " + (merger.mergeUser(users4) == 1));

        // Test 5: Three users, chain merge by 'b'
        List<Code07_MergeUserLearn.User> users5 = Arrays.asList(
            new Code07_MergeUserLearn.User("a1", "b1", "c1"),
            new Code07_MergeUserLearn.User("a2", "b1", "c2"),
            new Code07_MergeUserLearn.User("a3", "b1", "c3")
        );
        System.out.println("Test 5: " + (merger.mergeUser(users5) == 1));

        // Test 6: Merge by different fields
        List<Code07_MergeUserLearn.User> users6 = Arrays.asList(
            new Code07_MergeUserLearn.User("a1", "b1", "c1"),
            new Code07_MergeUserLearn.User("a2", "b1", "c2"),
            new Code07_MergeUserLearn.User("a3", "b3", "c1"),
            new Code07_MergeUserLearn.User("a4", "b4", "c4")
        );
        System.out.println("Test 6: " + (merger.mergeUser(users6) == 2));
    }

}
