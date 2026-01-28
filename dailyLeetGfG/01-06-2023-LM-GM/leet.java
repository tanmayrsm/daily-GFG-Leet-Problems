class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
         int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1; // There is no clear path from the start to the end
        }

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // Start from the top-left cell
        grid[0][0] = 1; // Mark the starting cell as visited

        int pathLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                if (row == n - 1 && col == n - 1) {
                    return pathLength; // Reached the bottom-right cell, return the path length
                }

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                        queue.offer(new int[]{newRow, newCol});
                        grid[newRow][newCol] = 1; // Mark the cell as visited
                    }
                }
            }

            pathLength++;
        }

        return -1; // There is no clear path from the start to the end
    }
}