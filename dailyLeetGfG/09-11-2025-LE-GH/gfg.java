class Solution {
    int n, ans = 0;
    public int chocolatePickup(int[][] mat) {
        // code here
        n = mat.length;
        int[] rem = solve(mat, 0, 0, 0, 0);
        if (rem[1] == 2)    return ans;
        return 0;
    }
    // int[] ret = [totalCost, isNCollected]
    private int[] solve(int[][] mat, int x, int y, int cost, int level) {
        if (x == n - 1 && y == n - 1) {
            System.out.println("level 1 ::" + (cost + mat[x][y]));
            return new int[] {cost + mat[x][y], level + 1};
        }
        if (x == 0 && y == 0 && level == 1) {
            System.out.println("level 2 ::" + (cost + mat[x][y]));
            ans = Math.max(ans, cost + mat[x][y]);
            return new int[] {cost + mat[x][y], level + 1};
        }
        int[] goDown = new int[] {-1, -1}, goRight = new int[] {-1, -1};

        if (x + 1 < n && mat[x + 1][y] > -1) {
            int old = mat[x][y];
            mat[x][y] = 0;
            goDown = solve(mat, x + 1, y, cost + mat[x][y], level);

            int[] goUp = new int[] {-1, -1}, goLeft = new int[] {-1, -1};
            if (goDown[1] == 1) {
                int maxi = -1;
                if (x - 1 >= 0 && mat[x - 1][y] > -1) {
                    int older = mat[x][y];
                    mat[x][y] = 0;
                    goUp = solve(mat, x - 1, y, cost + mat[x][y], 1);
                    maxi = Math.max(maxi, goUp[0]);
                    mat[x][y] = older;
                }
                if (y - 1 >= 0 && mat[x][y - 1] > -1) {
                    int older = mat[x][y];
                    mat[x][y] = 0;
                    goLeft = solve(mat, x, y - 1, cost + mat[x][y], 1);
                    maxi = Math.max(maxi, goLeft[0]);
                    mat[x][y] = older;
                }
                goDown[0] = Math.max(goDown[0], maxi);
            }
            mat[x][y] = old;
        }

        if (y + 1 < n && mat[x][y + 1] > -1) {
            int old = mat[x][y];
            mat[x][y] = 0;
            goRight = solve(mat, x, y + 1, cost + mat[x][y], level);

            int[] goUp = new int[] {-1, -1}, goLeft = new int[] {-1, -1};
            if (goRight[1] == 1) {
                int maxi = -1;
                if (x - 1 >= 0 && mat[x - 1][y] > -1) {
                    int older = mat[x][y];
                    mat[x][y] = 0;
                    goUp = solve(mat, x - 1, y, cost + mat[x][y], 1);
                    maxi = Math.max(maxi, goUp[0]);
                    mat[x][y] = older;
                }
                if (y - 1 >= 0 && mat[x][y - 1] > -1) {
                    int older = mat[x][y];
                    mat[x][y] = 0;
                    goLeft = solve(mat, x, y - 1, cost + mat[x][y], 1);
                    maxi = Math.max(maxi, goLeft[0]);
                    mat[x][y] = older;
                }
                goRight[0] = Math.max(goRight[0], maxi);
            }
            mat[x][y] = old;
        }

        return new int[] {Math.max(goDown[0], goRight[0]),
                (goDown[0] == 1 || goRight[1] == 1) ? 1 : 0};
    }
}
