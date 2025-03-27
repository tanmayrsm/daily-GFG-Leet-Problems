
class Solution
{
    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    
    private static class Train {
        int arrivalTime, departTime;
        public Train(int at, int dt) {
            this.arrivalTime = at;
            this.departTime = dt;
        }
    }
    private static class sortByArrival implements Comparator<Train> {
        @Override
        public int compare(Train t1, Train t2) {
            return t1.arrivalTime - t2.arrivalTime;
        }
    }
    
    static int findPlatform(int arr[], int dep[])
    {
        // add your code here
        int ans = 0, n = arr.length;
        Train[] t = new Train[n];
        for(int i = 0; i < n; i++) {
            t[i] = new Train(arr[i], dep[i]);
        }
        Arrays.sort(t, new sortByArrival());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(t[0].departTime);
        ans = 1;
        
        for(int i = 1; i < n; i++) {
            Train curr = t[i];
            if(curr.arrivalTime <= pq.peek()) {
                ans++;
            } else {
                pq.poll();
            }
            pq.add(curr.departTime);
        }
        
        return ans;
        
    }
    
}