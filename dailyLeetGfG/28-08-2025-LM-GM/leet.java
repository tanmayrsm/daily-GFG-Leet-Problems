class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        // put values in bottom left triangle
        List<Integer> currList = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            int k = 0;
            for (int j = 0; j < n - i; j++) {
                currList.add(grid[i + k][j]);
                k++;
            }

            Collections.sort(currList, Collections.reverseOrder());
            k = 0;
            for (int j = 0; j < n - i; j++) {
                grid[i + k][j] = currList.get(k++);
            }
            currList.clear();
        }
        currList.clear();

        // put values in top right triangle
        for (int j = 1; j < n; j++) {
            int k = 0;
            for (int i = 0; i < n - j; i++) {
                currList.add(grid[i][j + k]);
                k++;
            }
            Collections.sort(currList);

            k = 0;
            for (int i = 0; i < n - j; i++) {
                grid[i][j + k] = currList.get(k++);
            }
            currList.clear();
        }
        return grid;
    }
}