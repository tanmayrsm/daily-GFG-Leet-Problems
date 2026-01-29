import java.util.PriorityQueue;

class Solution {
    class Pair {
        int no, idx;
        Pair(int no, int idx) {
            this.no = no;
            this.idx = idx;
        }
    }
    public int[] arrayRankTransform(int[] arr) {
        // use PQ -> {idx, no}, sort by no
        // then at kth index(starting from 0), put arr[idx]
        int  k = 0, n = arr.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> Integer.compare(a.no, b.no));
        int[] ans = new int[n];
        for(int i = 0; i < n; i++)
            pq.offer(new Pair(arr[i], i));
        int lastIndex = -1;
        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if(lastIndex != -1 && (arr[lastIndex] == arr[idx])) {
                ans[idx] = k;
            } else {
                ans[idx] = ++k;
            }
            lastIndex = idx;
        }
        return ans;
    }
}