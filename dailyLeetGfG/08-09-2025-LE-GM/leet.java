class Solution {
    public int[] getNoZeroIntegers(int n) {
        int N = n-1;
        while(N > 0 && (containsZero(N) || containsZero(n - N))) {
            N--;
        }
        return new int[] {n - N, N};
    }
    private boolean containsZero(int n) {   // log 10^4 ~ 4 ~~ constant time complexity
        while(n > 0) {
            if (n % 10 == 0)    return true;
            n /= 10;
        }
        return false;
    }
}
