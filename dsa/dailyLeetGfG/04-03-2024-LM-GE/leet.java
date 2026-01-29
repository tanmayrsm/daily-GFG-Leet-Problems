class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        int ct = 0, ans = 0;
        Arrays.sort(tokens);
        int l = 0, r = tokens.length - 1;
        while(l <= r) {
            int oldL = l, oldR = r;
            // System.out.println(l + "::" + r + "::" + ct);
            while(l <= r && power - tokens[l] >= 0) {
                power -= tokens[l++];
                ct++;
            }
            ans = Math.max(ans, ct);
            if(ct > 0 &&  l <= r) {
                power += tokens[r--];
                ct--;
            }
            ans = Math.max(ans, ct);
            if(l == oldL && r == oldR)
                break;
        }
        return ans;
    }
}