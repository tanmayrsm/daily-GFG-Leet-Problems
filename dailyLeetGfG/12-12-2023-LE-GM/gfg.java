
class Solution{
    static int maxGold(int n, int m, int M[][])
    {
        // code here
        int ans = 0;
        for(int j = 1; j < m; j++) {
            for(int i = 0; i < n; i++) {
                M[i][j] += Math.max(
                    M[i][j - 1], 
                    Math.max(
                        i-1 >= 0 && j-1 >= 0 ? M[i-1][j-1] : 0, 
                        i+1 < n && j - 1 >= 0 ? M[i+1][j-1] : 0
                    )
                );
            }
        }
        for(int i = 0; i < n; i++)
            ans = Math.max(ans, M[i][m - 1]);
        return ans;
    }
}