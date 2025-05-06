package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPOLearn {
    
    public static class Program {
        public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
    }

    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program p1, Program p2) {
            return p1.c - p2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program p1, Program p2) {
            return p2.p - p1.p;
        }
    }


    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> pQCost = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> pQProfit = new PriorityQueue<>(new MaxProfitComparator());

        for(int i = 0; i < Profits.length; i++) {
            pQCost.add(new Program(Profits[i], Capital[i]));
        }

        for(int s = 0; s < K; s++) {
            while(!pQCost.isEmpty() && pQCost.peek().c <= W) {
                pQProfit.add(pQCost.poll());
            }
            if (pQProfit.isEmpty()) {
                return W;
            }
            W += pQProfit.poll().p;
        }
        return W;
    }
}
