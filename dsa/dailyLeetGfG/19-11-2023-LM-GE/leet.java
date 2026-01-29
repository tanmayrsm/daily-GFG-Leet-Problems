class Solution {
    // OBSERVATION - sort the array
    // and try getting answer for that array, u will observe a pattern
    // [1,3,5] -> [1,1,1]
    // 1 changed -> 0 time,
    // 3 changed -> 1 time,
    // 5 changed -> 2 time (first 3, then 1)
    // (if duplicate nos, add same 'x' time)
    
    public int reductionOperations(int[] nums) {
        int ans = 0;

        TreeMap<Integer, Integer> st = new TreeMap<>();
        for(int x : nums) {
            if(!st.containsKey(x)) st.put(x, 0);
            st.put(x, st.get(x) + 1);
        }

        int n = st.size(), ct = n;
        while(n > 0) {
            int key = st.lastKey();
            int val = st.get(key);
            st.remove(key);
            n--;
            ans += n * val;
        }
        
        return ans;
    }
}