package class14;

import java.util.HashSet;

public class Code01_LightLearn {


    public static int getMinLight(String road) {

        HashSet<Integer> lightSet = new HashSet<>();
        return process(road.toCharArray(), lightSet, 0);

    }

    public static int process(char[] road, HashSet<Integer> lightSet, int pos) {
        if (pos == road.length) {
            if (isAllLighted(road, lightSet)) {
                return lightSet.size();
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int yes = Integer.MAX_VALUE;


        int no = Math.min(process(road, lightSet,  pos + 1), yes);
        if (road[pos] != 'X') {
            lightSet.add(pos);
            yes = Math.min(yes, process(road, lightSet, pos + 1));
            lightSet.remove(pos);
        }
        
        
        return Math.min(yes, no);
    }

    public static int getMinLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] roadChar = road.toCharArray();
        int light = 0;
        int i = 0;
        while(i < road.length()) {
            if (roadChar[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 < road.length()) {
                    if (roadChar[i + 1] == 'X') {
                        i += 2;
                    } else {
                        i += 3;
                    }
                } else {
                    break;
                }
            }
        }
        return light;
    }

    public static boolean isAllLighted(char[] road, HashSet<Integer> lightSet) {
        boolean flag = true;
        for (int i = 0; i < road.length; i++) {
            char cur = road[i];
            if (cur != 'X') {
                if (!(lightSet.contains(i) || lightSet.contains(i - 1) || lightSet.contains(i + 1))) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static int getMinLight2(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        int light = 0;
        char[] roadChar = road.toCharArray();
        int pre = -1;
        int cur = 0;
        while(cur < road.length()) {
            if (roadChar[cur] == 'X') {
                int length = cur - pre - 1;
                light += (length % 3 == 0) ? (length / 3) : (length / 3 + 1);
                pre = cur;
            }
            cur++;
        }
        int length = cur - pre - 1;
        light += (length % 3 == 0) ? (length / 3) : (length / 3 + 1);
        return light;
    }

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
            // test = "X";
            // System.out.println(test);
			int ans1 = getMinLight(test);
            // System.out.println(ans1);
			int ans2 = getMinLight1(test);
            // System.out.println(ans2);
			int ans3 = getMinLight2(test);
            // System.out.println(ans3);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
