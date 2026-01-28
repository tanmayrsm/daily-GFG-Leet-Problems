class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = cells.length, l = 0, r = n - 1, ans = -1;
        Set<String> wateredCells = new HashSet<>();
        while (l <= r) {
            int mid = (l + r) / 2;
            if(check(wateredCells, row, col, cells, l, mid)) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        if (ans == -1)  return n;
        return ans + 1;
    }
    private boolean check(Set<String> st, int n, int m, int[][] cells, int l, int mid) {
        Set<String> newCp = new HashSet<>(st);
        for (int i = l; i <= mid; i++) {
            String key = cells[i][0] + "#" + cells[i][1];
            newCp.add(key); // water this cell
        }
        if(checkIfReachable(newCp, n, m)) {
            // water all cells from l to mid
            st.clear();        // Clear original set
            st.addAll(newCp);  // Add all elements from newCp
            return true;
        }
        return false;   // lets not water cells from l to mid
    }
    private boolean checkIfReachable(Set<String> st, int n, int m) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
            if(a[0] == b[0])    return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        }); // priortise higher row no
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Set<String> vis = new HashSet<>();
        for (int i = 0; i< m; i++) {
            String key = "0#" + String.valueOf(i);
            if(!st.contains(key)) { // if land
                pq.offer(new int[] {0, i});
            }
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if(curr[0] == n - 1)    return true;
            for (int[] d : dir) {
                int xx = d[0] + curr[0], yy = d[1] + curr[1];
                String key = String.valueOf(xx) + "#" + String.valueOf(yy);
                if(xx >= 0 && yy >= 0 && xx < n && yy < m && !st.contains(key) && !vis.containsKey(key)) {
                    vis.add(key);
                    pq.offer(new int[] {xx, yy});
                }
            }
        }
        return false;
    }
}

/*
check for cells length from 0 to m, binary search for index, where its not possible to go from row1 to rowM
so
for each mid ->
- possible to go -> l = mid + 1, let 2D array with water stay as it is, and add from old mid to new mid
- impossible -> ans = mid, r = mid - 1, remove entries from new mid to oldMid

- checkIfreachable() -> n * m
 */