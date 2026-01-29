
class Solution {
    public int maxUniqueSplit(String s) {
        return solve(s, new HashSet<>(), new StringBuilder(), 0, s.length());
    }
    private int solve(String s, Set<String> set, StringBuilder sb, int curr, int n) {
        if(curr == n)  {
            if(sb.isEmpty())    return 0;
            return !set.contains(sb.toString()) ? 1 : Integer.MAX_VALUE;
        }

        int dontSplit = 0, okSplit = 0;
        String newer = sb.toString() + (s.charAt(curr) + "");
        
        dontSplit = solve(s, set, new StringBuilder(newer), curr + 1, n);

        if(!set.contains(newer)) {  // if set does not has newly formed string, split here
            Set<String> st = new HashSet<>(set);
            st.add(newer);
            int r = solve(s, st, new StringBuilder(), curr + 1, n);
            if(r != Integer.MAX_VALUE)
                okSplit = 1 + r;
        }
        return Math.max(dontSplit, okSplit);
        
    }
}