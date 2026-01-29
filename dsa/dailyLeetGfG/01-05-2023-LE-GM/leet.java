class Solution {
    public double average(int[] salary) {
        double r = 0;
        int n = salary.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            r += salary[i];
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
        }
        r -= (min + max);
        return r / (n - 2);
    }
}