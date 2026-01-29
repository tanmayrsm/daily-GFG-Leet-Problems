
class Solution {
    private static int[][] dir = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    int shortestDistance(int N, int M, int A[][], int X, int Y) {
        // code here
        boolean[][] vis = new boolean[N][M];
        int ans = 0;
        if(A[0][0] == 0 || A[X][Y] == 0)    return -1;
        if(X == 0 && Y == 0)    return 0;
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> p = new ArrayList<>();
        p.add(0);
        p.add(0);
        q.offer(p);
        vis[0][0] = true;
        while(!q.isEmpty()) {
            int n = q.size();
            ans++;
            while(n-- > 0) {
                ArrayList<Integer> node = q.poll();
                for(int[] x :  dir) {
                    int newX = node.get(0) + x[0];
                    int newY = node.get(1) + x[1];
                    if(isValid(A, newX, newY, vis, N, M)) {
                        if(newX == X && newY == Y)
                            return ans;
                        ArrayList<Integer> rem = new ArrayList<>();
                        rem.add(newX);
                        rem.add(newY);
                        q.offer(rem);
                        vis[newX][newY] = true;
                    }
                }
            }
        }
        return -1;

    }
    private static boolean isValid(int A[][], int i, int j, boolean[][] vis, int N, int M) {
        if(i < 0 || j < 0 || i >= N ||  j >= M || vis[i][j] || A[i][j] == 0)
            return false;
        return true;
    }
};