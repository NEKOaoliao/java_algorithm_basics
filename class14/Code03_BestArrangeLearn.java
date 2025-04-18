package class14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Code03_BestArrangeLearn {
    
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //brutal
    public static int bestArrange1(Program[] programs) {
        HashSet<Program> used = new HashSet<>();
        return process1(0, programs, 0, used);
    }

    public static int process1(int done, Program[] programs, int timeLine, HashSet<Program> used) {
        if (used.size() == programs.length) {
            return done;
        }

        int max = done;
        for(int i = 0; i < programs.length; i++) {
            if (!used.contains(programs[i]) && programs[i].start >= timeLine) {
                used.add(programs[i]);
                max = Math.max(max, process1(done + 1, programs, programs[i].end, used));
                used.remove(programs[i]);
            }
        }
        return max;
    }

    public static class MyProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program a, Program b) {
            return a.end - b.end;
        }
    }

    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new MyProgramComparator());

        int ans = 0;
        int timeLine = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                ans++;
                timeLine = programs[i].end;
            }
        }
        return ans;
    }

    	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int programSize = 3;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
            int ans1 = bestArrange1(programs);
            int ans2 = bestArrange2(programs);
			if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
