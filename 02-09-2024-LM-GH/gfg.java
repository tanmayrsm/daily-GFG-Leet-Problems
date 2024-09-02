
class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int minimumCostPath(int[][] grid) {
        int n = grid.length;
        
        // DP array to store the minimum cost to reach each cell
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = grid[0][0];
        
        // Priority queue to process cells in order of their minimum cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0], x = current[1], y = current[2];
            
            // If we've reached the bottom-right cell, return the cost
            if (x == n - 1 && y == n - 1) {
                return cost;
            }
            
            // Explore all possible directions
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0], newY = y + dir[1];
                
                // Check if the new position is within bounds
                if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                    int newCost = cost + grid[newX][newY];
                    
                    // If a cheaper cost to reach (newX, newY) is found, update dp and push to pq
                    if (newCost < dp[newX][newY]) {
                        dp[newX][newY] = newCost;
                        pq.offer(new int[]{newCost, newX, newY});
                    }
                }
            }
        }
        
        return dp[n-1][n-1];
    }
}
