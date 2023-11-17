import java.util.Set;

class Solution {
    private static String ans;
    public String findDifferentBinaryString(String[] nums) {
        int n = nums[0].length();
        if(n == 1) {
            if(nums[0].equals("0")) return "1";
            return "0";
        }

        Set<String> st = new HashSet<>();
        char[] start = new char[n];
        ans = "";

        Arrays.fill(start,  '0');
        for(String g  : nums)   
            st.add(g);

        boolean d = solve(n - 1, start, st);
        
        return ans;
    }
    private static boolean solve(int idx, char[] curr, Set<String> st) {
        if(idx < 0) return false;
        // change charAt idx or not

        curr[idx] = '1';
        String newer = new String(curr);
        if(!st.contains(newer)) {
            ans = newer;
            return true;
        }
        // change
        boolean swap = solve(idx - 1, curr, st);
        if(swap)    return swap;    // no further computation, if true

        // dont change
        curr[idx] = '0';
        boolean notswap = solve(idx - 1, curr, st);

        return swap || notswap;
    }
    
}