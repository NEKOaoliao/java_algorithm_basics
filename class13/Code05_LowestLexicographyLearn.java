package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class Code05_LowestLexicographyLearn {
    
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        HashSet<Integer> used = new HashSet<>();
        TreeSet<String> all = new TreeSet<>();
        process0(strs, used, all, "");
        return all.size() == 0 ? "" : all.first();
    }

    public static void process0(String[] strs, HashSet<Integer> used, TreeSet<String> all, String mergedStr) {
        if (used.size() == strs.length) {
            all.add(mergedStr);
            return;
        }
        for(int i = 0; i < strs.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            used.add(i);
            process0(strs, used, all, mergedStr + strs[i]);
            used.remove(i);
        }
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = generateRandomStringArray(arrLen, strLen);
			String[] arr2 = copyStringArray(arr1);
			if (!lowestString(arr1).equals(lowestString2(arr2))) {
				for (String str : arr1) {
					System.out.print(str + ",");
				}
				System.out.println();
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
