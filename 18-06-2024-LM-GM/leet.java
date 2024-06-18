import java.util.PriorityQueue;

class Solution {
    class Pair {
        int difficulty;
        int profit;
        Pair(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Pair[] jobs = new Pair[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.profit - a.profit);
        for (int i = 0; i < n; i++) {
            jobs[i] = new Pair(difficulty[i], profit[i]);
            pq.add(jobs[i]);
        }
        Arrays.sort(worker);
        int ans = 0;
        for (int i = worker.length - 1; i >= 0; i--) {
            while (!pq.isEmpty() && pq.peek().difficulty > worker[i]) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                ans += pq.peek().profit;
            }
        }
        return ans;   
    }
}