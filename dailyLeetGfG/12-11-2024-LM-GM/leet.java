import java.util.TreeSet;

class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] ans = new int[queries.length];
        Arrays.sort(items, (int[] a, int[] b) -> {
            if (a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        int maxo = 0;
        for(int[] item : items) {
            maxo = Math.max(maxo, item[1]);
            ts.put(item[0], maxo);
        }
        System.out.println(ts);
        for(int i = 0; i < queries.length; i++) {
            if(ts.containsKey(queries[i]))
                ans[i] = ts.get(queries[i]);
            else {
                ts.put(queries[i], 0);
                Integer lower = ts.lowerKey(queries[i]);
                if(lower != null) {
                    Integer val = ts.get(lower);
                    ans[i] = val;
                    ts.put(queries[i], val);
                }
            }
        }
        return ans;
    }
}

// [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
// 1 : 2
// 2 : max(2, 1 ka beauty) : 4
// 3 : 4->5
// 5 : 6