class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length(), ans = 0;
        int[][] ct = new int[n][26];
        int[] lastPos = new int[26];
        boolean[] vis = new boolean[26];

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            ct[i][idx]++;
            if (i > 0) {
                for (int j = 0; j < ct[0].length; j++)
                    ct[i][j] += ct[i - 1][j];
            }
            lastPos[idx] = i;
        }

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (!vis[idx]) {
                vis[idx] = true;
                if (lastPos[idx] - i > 1) {
                    int r = lastPos[idx] - 1, l = i;
                    for (int j = 0; j < 26; j++) {
                        // System.out.println(ct[l][j] + "::" + ct[r][j] + "::" + j);
                        if(ct[r][j] - ct[l][j] > 0) {
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;

    }
}

// b b c b a b a
//
