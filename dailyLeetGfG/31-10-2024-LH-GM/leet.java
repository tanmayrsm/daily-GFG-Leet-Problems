import java.util.Arrays;
import java.util.List;

class Solution {    // reff
    private Long[][] dp;

    private long dfs(int robotIdx, int factoryIdx, int[] robot, int[][] factory) {
        if (robotIdx == robot.length) return 0;
        if (factoryIdx == factory.length) return Long.MAX_VALUE;
        if (dp[robotIdx][factoryIdx] != null) return dp[robotIdx][factoryIdx];

        long res = dfs(robotIdx, factoryIdx + 1, robot, factory);
        long cost = 0;

        for (int k = 0; k < factory[factoryIdx][1] && robotIdx + k < robot.length; ++k) {
            cost += Math.abs(robot[robotIdx + k] - factory[factoryIdx][0]);
            long next = dfs(robotIdx + k + 1, factoryIdx + 1, robot, factory);
            if (next != Long.MAX_VALUE) {
                res = Math.min(res, cost + next);
            }
        }

        return dp[robotIdx][factoryIdx] = res;
    }

    public long minimumTotalDistance(List<Integer> robotList, int[][] factory) {
        int[] robot = robotList.stream().mapToInt(i -> i).toArray();

        Arrays.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        dp = new Long[robot.length][factory.length];
        return dfs(0, 0, robot, factory);
    }
}
