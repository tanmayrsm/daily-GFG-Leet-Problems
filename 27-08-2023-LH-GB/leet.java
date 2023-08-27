class Solution {
    static Map<Integer, Integer> pos;
    static int n;
    static Map<String, Boolean> dp;
    public boolean canCross(int[] stones) {
        pos = new HashMap<>();
        n = stones.length;
        dp = new HashMap<>();

        for(int i = 0; i < n; i++)
            pos.put(stones[i], i);
        return solve(stones, 0, stones[0]);
    }

    private static boolean solve(int[] stones, int lastUnit, int currPos) {
        if(pos.get(currPos) == null)    return false;   // its water
        if(pos.get(currPos) == n - 1)   return true;    // reached on last stone
        String key = getKey(lastUnit, currPos);

        if(dp.get(key) != null)
            return dp.get(key);

        boolean nMinusOne = false, nPlusOne = false, nJump = false;
        nMinusOne = lastUnit != 1 ? solve(stones, lastUnit - 1 , currPos + (lastUnit - 1)) : false; 
        nJump = lastUnit != 0 ? solve(stones, lastUnit , currPos + lastUnit) : false; 
        nPlusOne = solve(stones, lastUnit + 1 , currPos + (lastUnit + 1)); 
        
        Boolean val = nMinusOne || nJump || nPlusOne;
        dp.put(key, val);
        return val;
    }

    private static String getKey(int a, int b) {
        StringBuilder s = new StringBuilder("");
        s.append(String.valueOf(a));
        s.append("$");
        s.append(String.valueOf(b));
        return s.toString();
    }

}