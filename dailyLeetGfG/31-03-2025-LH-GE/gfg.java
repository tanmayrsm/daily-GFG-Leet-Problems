
class Solution {
    public int maxPartitions(String s) {
        // code here
        int n = s.length(), maxPos = -1;
        int[] lastPos = new int[26];
        StringBuilder sb = new StringBuilder();
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(lastPos, -1);
        
        for (int i = n - 1; i >= 0; i--) {
            int currPos = s.charAt(i) - 'a';
            if(lastPos[currPos] == -1)
                lastPos[currPos] = i;
        }
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (i > maxPos && maxPos != -1) {
                ans.add(sb.length());
                sb = new StringBuilder();
            }
            sb.append(curr + "");
            maxPos = Math.max(maxPos, lastPos[curr - 'a']);
        }
        
        // if (!sb.isEmpty())
        ans.add(sb.length());
        return ans.size();
    }
}