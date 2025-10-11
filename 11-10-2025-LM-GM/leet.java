class Solution {
    HashMap<Integer,Integer> map;
    Long dp[];

    public long solve(int i, int[] arr) {
        if (i >= arr.length) return 0;
        if (dp[i] != null) return dp[i];

        long skip = solve(i + 1, arr);
        long take = (long) arr[i] * map.get(arr[i]);
        int j = i + 1;

        while (j < arr.length && arr[j] - arr[i] <= 2) j++;
        take += solve(j, arr);

        return dp[i] = Math.max(skip, take);
    }

    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        map = new LinkedHashMap<>();
        for (int i : power) map.put(i, map.getOrDefault(i, 0) + 1);

        int[] arr = new int[map.size()];
        int k = 0;
        for (int i : map.keySet()) arr[k++] = i;

        dp = new Long[arr.length];
        return solve(0, arr);
    }
}