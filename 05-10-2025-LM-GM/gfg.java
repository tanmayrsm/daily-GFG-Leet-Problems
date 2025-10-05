class Solution {
    ArrayList<String> ans;
    boolean[][] vis;
    int n;
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        n = maze.length;
        ans = new ArrayList<>();
        vis = new boolean[n][n];
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) return ans;
        solve(maze, 0, 0, new StringBuilder());
        return ans;
    }
    private void solve(int[][] maze, int x, int y, StringBuilder curr) {
        if (x == n -1 && y == n - 1) {
            ans.add(curr.toString());
            return;
        }
        vis[x][y] = true;
        StringBuilder orig = new StringBuilder(curr);

        // Lexicographically wise ->
        // go down
        if (isValid(x + 1, y, maze)) {
            curr.append("D");
            solve(maze, x + 1, y, curr);
            curr = orig;
        }

        // go left
        StringBuilder left = new StringBuilder(orig);
        if (isValid(x, y - 1, maze)) {
            left.append("L");
            solve(maze, x, y - 1, left);
            left = orig;
        }

        // go right
        StringBuilder right = new StringBuilder(orig);
        if (isValid(x, y + 1, maze)) {
            right.append("R");
            solve(maze, x, y + 1, right);
            right = orig;
        }

        // go up
        StringBuilder upper = new StringBuilder(orig);
        if (isValid(x - 1, y, maze)) {
            upper.append("U");
            solve(maze, x - 1, y, upper);
            upper = orig;
        }

        vis[x][y] = false;
    }
    private boolean isValid(int x, int y, int[][] maze) {
        if (x < 0 || y < 0 || x >= n || y >= n || vis[x][y] || maze[x][y] == 0)    return false;
        return true;
    }
}