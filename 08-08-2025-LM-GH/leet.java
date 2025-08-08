class Solution {    // referred soln
    private double[][] mem;
    public double soupServings(int n) {
        if (n > 5000) return 1.0;
        n = (n + 24) / 25;
        mem = new double[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(mem[i],-1);
        double ans = solve(n, n);
        return ans;
    }

    private double solve(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (b <= 0) return 0;
        if (a <= 0) return 1;
        if (mem[a][b] != -1.0) return mem[a][b];
        double ans = 0.25 * (solve(a-4, b) + solve(a-3,b-1) + solve(a-2,b-2) + solve(a-1,b-3));
        return mem[a][b] = ans;
    }
}