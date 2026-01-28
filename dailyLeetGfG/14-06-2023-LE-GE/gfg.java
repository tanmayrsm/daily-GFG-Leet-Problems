
class Solution {
    static long maxDiamonds(int[] A, int N, int K) {
        // code here
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> y - x);    // to keep max no at top
        for(int x : A)
            pq.offer(x);
        while(K-- > 0) {
            int no = pq.poll();
            if(no == 0)
                break;
            ans += no;
            pq.offer(no / 2);
        }
        return ans;
    }
};