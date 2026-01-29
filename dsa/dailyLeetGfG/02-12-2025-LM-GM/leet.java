class Solution {
    public int countTrapezoids(int[][] points) {
        // Optimized Approach :
        // T.C = O(N)
        // S.C = O(N)
        // Use HashMap to count same set elements having identical 'y-coordinates'.
        // Then make the pairing accordingly.

        HashMap<Integer,Integer> map = new HashMap<>();
        int n = points.length;
        for(int i = 0; i < n; i++) {
            map.put(points[i][1],map.getOrDefault(points[i][1],0)+1);
        }

        long ans = 0, sum = 0;
        int mod = (int)(1e9+7);
        for(int key : map.keySet()) {
            long count = map.get(key);
            long pairs = (count)*(count-1)/2;
            ans = ans%mod + (pairs * sum)%mod;
            sum += pairs;
        }
        return (int)ans;
    }
}
//
//3
//1
//3
//
//ans=0, sum=3
//ans=0 + 3, sum=4
//ans=3 + 4*3, sum=7