class Solution {
    private static int ans;
    private static final int MOD = 1000000007;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        ans = 0;
        int[][] dp = new int[locations.length + 1][fuel + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);

        return solve(start, fuel, finish, locations, dp);
    }
    private static int solve(int currPtr, int currFuel, int fin, int[] locations, int[][] dp) {
        if(currFuel < 0)    return 0;
        if(dp[currPtr][currFuel] != -1)
            return dp[currPtr][currFuel];

        int total = 0;
        if(currPtr == fin) {
            total++;
        }

        for(int i = 0; i < locations.length; i++) {
            if(i != currPtr) {
                int fuelNeeded = Math.abs(locations[currPtr] - locations[i]);
                if(currFuel - fuelNeeded >= 0) {
                    total = (total + solve(i, currFuel - fuelNeeded, fin, locations, dp)) % MOD;
                }
            }
        }
        return dp[currPtr][currFuel] = total % MOD;
    }
}