class Solution {
    public int findMinDifference(List<String> timePoints) {
        int ans = Integer.MAX_VALUE;
        Collections.sort(timePoints, (String t1, String t2) -> {
            String[] p1 = t1.split(":"), p2 = t2.split(":");
            int h1 = Integer.parseInt(p1[0]), m1 = Integer.parseInt(p1[1]);
            int h2 = Integer.parseInt(p2[0]), m2 = Integer.parseInt(p2[1]);
            if (h1 != h2)   return Integer.compare(h1, h2);
            return Integer.compare(m1, m2);
        });
        List<Integer> tp = timePoints.stream().map(item -> {
            String[] g = item.split(":");
            Integer hr = Integer.parseInt(g[0]) * 60 + Integer.parseInt(g[1]);
            return hr;
        }).collect(Collectors.toList());
        int m = tp.size();
        for (int j = 0; j < m; j++)
            tp.add(1440 + tp.get(j));
        for(int i = 1; i < m * 2; i++)
            ans = Math.min(ans, Math.abs(tp.get(i - 1) - tp.get(i)));
        return ans;

    }
}