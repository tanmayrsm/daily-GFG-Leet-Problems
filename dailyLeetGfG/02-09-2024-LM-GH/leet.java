class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        long sum = Arrays.stream(chalk).mapToLong(i -> i).sum(), n = chalk.length;

        for (int i = 0;  ;i++) {
            if (i == n) i = 0;
            if (chalk[i] > k)   return i;
            k -= chalk[i];
        }
    }
}