class Solution {
    public int numberOfPairs(int[][] points) {
        int ans = 0, n = points.length;
        Arrays.sort(points, (a, b) -> {
            // First compare by x-coordinate (a[0] vs b[0])
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            // If x-coordinates are the same, store in descending order of y
            return Integer.compare(b[1], a[1]);
        });
        System.out.println();
        for (int i = n - 1; i >= 0; i--) {
            int x = points[i][0], y = points[i][1]; // possible Bob
            // System.out.println("<Bob>" + x + "," + y);
            int yWorked = Integer.MIN_VALUE;   // makes sure that whatever y worked, the next y on LHS, is lower than this
            for (int j = i - 1; j >= 0; j--) {
                int xx = points[j][0], yy = points[j][1];   // possible Alice
                // System.out.println("<Alice>" + xx + "," + yy);
                if (xx <= x && yy >= y && (yWorked == Integer.MIN_VALUE || yy < yWorked)) {
                    // System.out.println("Adding alice");
                    ans++;
                    yWorked = yy;
                }
            }
        }

        return ans;
    }


}