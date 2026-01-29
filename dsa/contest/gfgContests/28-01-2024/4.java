
class Solution {
    public static int PossibleStrings(int N, int K) {
        // code here
        long ans = 1;
        int mod = 1000000007;
        for(int i = 0; i < N; i++) {
            ans *= 26;
            ans %= mod;
        }
        return (int)(ans - 26);
    }
}