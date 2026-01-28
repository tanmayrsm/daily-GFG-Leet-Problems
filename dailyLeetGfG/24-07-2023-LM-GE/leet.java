class Solution {
    public double myPow(double x, int n) {
        int N = Math.abs(n);
        double ans = solve(x, n);
        return n < 0 ? 1 / ans :  ans;
    }
    private static double solve(double x, int n) {
        if(n == 0) {
            return 1;
        }
        double temp = solve(x,  n / 2);
        temp  = temp * temp;
        if(n % 2 == 0)
            return temp;
        return temp * x;
    }
}