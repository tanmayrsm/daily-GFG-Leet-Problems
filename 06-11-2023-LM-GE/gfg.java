
class Solution{
    static List<Integer> matrixSum(int n, int m, int mat[][], int q, int queries[][])
    {
        // code here
        List<Integer> ans = new ArrayList<>();
        int[][] dOneHop = new int[][] {{0, 1},{0, -1},{1, 0},{-1, 0},{1, 1},{-1, 1},{1, -1},{-1, -1}};
        int[][] dTwoHop = new int[][] { 
                                        {-2, -2}, {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2},
                                        {-1, -2}, {-1, 2},
                                        {0, -2},  {0, 2},
                                        {1, -2}, {1, 2},
                                        {2, -2}, {2, -1}, {2, 0}, {2, 1}, {2, 2},
                                    };
        for(int[] x : queries) {
            if(x[0] == 1)
                ans.add(hop(mat, n, m, x[1], x[2], dOneHop));
            else ans.add(hop(mat, n, m, x[1], x[2], dTwoHop));
        }
        return ans;
    }
    private static int hop(int[][] mat, int n, int m, int x, int y, int res[][]) {
        int ans = 0;
        for(int[] d : res) {
            int xx = x + d[0], yy = y + d[1];
            if(isValid(xx, yy, n, m))
                ans += mat[xx][yy];
        }   
        return ans;
    }

    private static boolean isValid(int x, int y, int n, int m) {
        if(x < 0 || y < 0 || x >= n || y >= m)  return false;
        return true;
    }
}