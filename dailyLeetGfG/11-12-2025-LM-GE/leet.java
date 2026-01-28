class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, Integer[]> rangeAtX = new HashMap<>();
        Map<Integer, Integer[]> rangeAtY = new HashMap<>();
        int ans = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (!rangeAtX.containsKey(y)) {
                rangeAtX.put(y, new Integer[] {Integer.MAX_VALUE, Integer.MIN_VALUE});
            }
            Integer[] rem = rangeAtX.get(y);
            rem[0] = Math.min(rem[0], x);
            rem[1] = Math.max(rem[1], x);

            if (!rangeAtY.containsKey(x)) {
                rangeAtY.put(x, new Integer[] {Integer.MAX_VALUE, Integer.MIN_VALUE});
            }
            rem = rangeAtY.get(x);
            rem[0] = Math.min(rem[0], y);
            rem[1] = Math.max(rem[1], y);
        }

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if ((rangeAtX.containsKey(y) && x > rangeAtX.get(y)[0] && x < rangeAtX.get(y)[1]) && (rangeAtY.containsKey(x) && y > rangeAtY.get(x)[0] && y < rangeAtY.get(x)[1]) ) {
                ans++;
            }
        }
        return ans;
    }
}