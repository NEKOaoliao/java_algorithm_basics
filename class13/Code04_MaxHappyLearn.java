package class13;

import java.util.ArrayList;
import java.util.List;

public class Code04_MaxHappyLearn {
    
    public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}
	}

    public static class Info {
        public int comeMax;
        public int notComeMax;

        public Info(int cm, int ncm) {
            this.comeMax = cm;
            this.notComeMax = ncm;
        }
    }

    public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	// 当前来到的节点叫cur，
	// up表示cur的上级是否来，
	// 该函数含义：
	// 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	// 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	public static int process1(Employee cur, boolean up) {
		if (up) { // 如果cur的上级来的话，cur没得选，只能不来
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}

    public static int getMaxHappy(Employee boss) {
        Info resultsInfo = process(boss);
        
        return Math.max(resultsInfo.comeMax, resultsInfo.notComeMax);
    }

    public static Info process(Employee ppl) {
        if (ppl == null) {
            return new Info(0,0);
        }

        int comeMax = ppl.happy;
        int notComeMax = 0;

        // get stuff's info
        for (Employee stuff: ppl.nexts) {
            Info curStuffInfo = process(stuff);
            comeMax += curStuffInfo.notComeMax;
            notComeMax += Math.max(curStuffInfo.comeMax, curStuffInfo.notComeMax);
        }

        return new Info(comeMax, notComeMax);

    }

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 500000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != getMaxHappy(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
