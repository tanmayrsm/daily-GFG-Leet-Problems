class Solution {
	public int maxValue(int[][] events, int k) {
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		int n = events.length;

		dp = new int[k + 1][n];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		return dfs(0, k, events);
	}

	private int[][] dp;

	private int dfs(int ci, int count, int[][] events) {
		if (count == 0 || ci == events.length) {
			return 0;
		}
		if (dp[count][ci] != -1) {
			return dp[count][ci];
		}
		int ni = func(events, events[ci][1]);
		dp[count][ci] = Math.max(dfs(ci + 1, count, events),
				events[ci][2] + dfs(ni, count - 1, events));
		return dp[count][ci];
	}

	public static int func(int[][] events, int target) {
		int left = 0, right = events.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (events[mid][0] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}