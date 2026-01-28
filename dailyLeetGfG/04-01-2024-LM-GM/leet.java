class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int ans = 0;
        for(int x : nums) 
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        
        for(Map.Entry<Integer, Integer> x : mp.entrySet()) {
            int val = x.getValue();
            if(val == 1)    return -1;

            
            ans += val / 3; // to minimise answer, divivde more of them by 3

            // BIG question for below code
            //eg ct = 28 => 3*8 + 2*2 (optimal)
            // 28 / 3 = 9
            // as 28 % 3 != 0,
            // ans++ => 10

            // anything % 3 can be 0,1,2
            // 5 % 3 => 2 => so ct = 3 + 2 i.e 2
            // 4%3 => 1 => so ct = 2 + 2 OR 4/3 + 1
            if(val % 3 != 0) ans++;   
            
        }

        return ans;
    }
}