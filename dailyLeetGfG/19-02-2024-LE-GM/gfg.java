
// User function Template for Java

class Solution{
    static int minValue(String s, int k){
        // code here
        int[] ct = new int[26];
        int n = s.length(), ans = 0;
        for(int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int x : ct)
            pq.offer(x);
        
        
        while(k > 0) {
            int maxNo = pq.poll();
            // System.out.println("max ::" + maxNo + "::" + k);
            if(maxNo - k < 0) {
                k--;
                pq.offer(maxNo - 1);
            } else if(k > 0) {
                int rem = maxNo - 1;
                k--;
                pq.offer(rem);
            }
        }
        while(!pq.isEmpty()) {
            int elem = pq.poll();
            ans += elem * elem;
        }
            
        return ans;
    }
}