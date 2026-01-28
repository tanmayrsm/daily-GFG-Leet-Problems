

//User function Template for Java

class Solution
{
    static void dfs(int[][] matrix, boolean[][] visited,
                    int x, int y, int n, int m)
    {
        // If the land is already visited
        // or there is no land or the
        // coordinates gone out of matrix
        // break function as there
        // will be no islands
        if (x < 0 || y < 0 || x >= n || y >= m
            || visited[x][y] == true || matrix[x][y] == 0)
            return;
 
        // Mark land as visited
        visited[x][y] = true;
 
        // Traverse to all adjacent elements
        dfs(matrix, visited, x + 1, y, n, m);
        dfs(matrix, visited, x, y + 1, n, m);
        dfs(matrix, visited, x - 1, y, n, m);
        dfs(matrix, visited, x, y - 1, n, m);
    }
 
    // Function that counts the closed island
    static int countClosedIsland(int[][] matrix, int n,
                                 int m)
    {
 
        // Create boolean 2D visited matrix
        // to keep track of visited cell
 
        // Initially all elements are
        // unvisited.
        boolean[][] visited = new boolean[n][m];
 
        // Mark visited all lands
        // that are reachable from edge
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
 
                // Traverse corners
                if ((i * j == 0 || i == n - 1 || j == m - 1)
                    && matrix[i][j] == 1
                    && visited[i][j] == false)
                    dfs(matrix, visited, i, j, n, m);
            }
        }
 
        // To stores number of closed islands
        int result = 0;
 
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
 
                // If the land not visited
                // then there will be atleast
                // one closed island
                if (visited[i][j] == false
                    && matrix[i][j] == 1) {
                    result++;
 
                    // Mark all lands associated
                    // with island visited.
                    dfs(matrix, visited, i, j, n, m);
                }
            }
        }
 
        // Return the final count
        return result;
    }
    public int closedIslands(int[][] matrix, int N, int M)
    {
        // Code here
        return countClosedIsland(matrix, N, M);
    }
}