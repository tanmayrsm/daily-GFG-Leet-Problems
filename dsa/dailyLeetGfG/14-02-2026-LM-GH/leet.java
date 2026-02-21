class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] prevRow = new double[1];
        prevRow[0] = poured;

        for (int row = 1; row <= query_row; row++) {
            double[] currRow = new double[row + 1];

            for (int i = 0; i < row; i++) {
                double remaining = prevRow[i] - 1.0;
                if (remaining > 0) {
                    currRow[i] += 0.5 * remaining;
                    currRow[i + 1] += 0.5 * remaining;
                }
            }

            prevRow = currRow;
        }

        return Math.min(1.0, prevRow[query_glass]);
    }
}