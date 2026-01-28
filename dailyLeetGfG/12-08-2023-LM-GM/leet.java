class Solution {
    public int uniquePathsWithObstacles(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        if(arr[0][0] == 1 || arr[n - 1][m - 1] == 1)    return 0;
        int[][] ans = new int[n][m];
        for(int x[] : ans)  Arrays.fill(x, -1);
        // ans[n - 1][m - 1] = 1;

        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                if(arr[i][j] != 1 && i != n - 1 && j != m - 1) {
                    int costRight = isValid(arr, i, j + 1, n, m) ? ans[i][j + 1] : 0;
                    int costBelow = isValid(arr, i + 1, j, n, m) ? ans[i + 1][j] : 0;
                    ans[i][j] = costRight + costBelow;

                }
            }
        }
        return ans[0][0];
        // return solve(arr, 0, 0, n, m, ans);
    }

    private static boolean isValid(int[][] arr, int i, int j, int n, int m) {
        if(i < 0 || j < 0 || i >= n || j >= m || arr[i][j] == 1)
            return false;
        return true;
    }

    private static int solve(int[][] arr, int x, int y, int n, int m, int[][] ans) {
        if(x == n - 1 && y == m - 1) {
            return 1;
        }
        if(ans[x][y] != -1) return ans[x][y];
        int right = isValid(arr, x, y + 1, n, m) ? solve(arr, x, y + 1, n, m, ans) : 0;
        int down = isValid(arr, x + 1, y, n, m) ? solve(arr, x + 1, y, n, m, ans) : 0;
        ans[x][y] = right + down;
        return ans[x][y];
    }
}