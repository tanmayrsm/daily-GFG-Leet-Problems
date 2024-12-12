
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;
        for(int gift : gifts)
            pq.offer(Long.valueOf(gift));
        while (k-- > 0) {
            long no = (long) Math.sqrt(pq.poll());
            pq.offer(no);
        }
        while(!pq.isEmpty())
            ans += pq.poll();
        return ans;
    }
}