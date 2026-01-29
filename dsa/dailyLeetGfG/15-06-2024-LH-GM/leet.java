class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int proj[][] = new int[n][2];
        for(int i = 0; i < n; i++) {
            proj[i][0] = capital[i];
            proj[i][1] = profits[i];
        }
        Arrays.sort(proj, (a,b) -> Integer.compare(a[0], b[0]));    // sort array as per capitals
        int i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    // pq with largets element at head

        while(k-- > 0) {
            while(i < n && proj[i][0] <= w) {
                pq.offer(proj[i][1]);      // add profit in pq
                i++;
            }
            if(pq.isEmpty())    break;      // all proj in pq have capital more than current 'w'
            w += pq.poll();
        }

        return w;

    }
}