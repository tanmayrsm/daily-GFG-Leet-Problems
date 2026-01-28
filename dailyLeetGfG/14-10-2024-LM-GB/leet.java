import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;
        for(int num : nums)
            pq.offer(num);
        while (k-- > 0) {
            int no = pq.poll();
            ans += no;
            int fact = no % 3 == 0 ? no / 3 : (no / 3) + 1;
            pq.offer(fact);
        }
        return ans;
    }
}