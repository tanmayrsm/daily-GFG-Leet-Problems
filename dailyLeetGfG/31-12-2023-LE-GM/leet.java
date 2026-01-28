class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length();
        int[][] store = new int[26][2];
        int ans = 0;
        for(int[] x : store)    Arrays.fill(x, -1);

        // store pos L to R
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(store[c - 'a'][0] == -1)
                store[c - 'a'][0] = i;
        }

        // store pos R to L
        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int ind = c - 'a';
            if(store[ind][1] == -1)
                store[ind][1] = i;
            ans = Math.max(ans, Math.abs(store[ind][1] - store[ind][0]));
        }

        return ans - 1;
        
    }
}