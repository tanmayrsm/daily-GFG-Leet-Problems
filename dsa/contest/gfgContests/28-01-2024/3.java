
class Solution {
    private static Map<String, Integer> dp;
    public static int MaximumEnergy(int N, int[] E, int K, int[] P) {
        // code here
        long[] pref = new long[N];
        Map<Integer, Integer> indexes = new HashMap<>();
        long ans = 0;
        int mod = 1000000007;
        pref[0] = E[0];
        for(int i = 1; i < N; i++)
            pref[i] = pref[i - 1] + E[i];
        // actual intent - try to get more overlapping in 'P'
        // ie. - 2 3 0 1
        // sort -> 0 1 2 3
        // take 0 & 3
        // then take 1 & 2 (which was already in 0 & 3 wala pair)
        // hence ensure max overlap between indexes by sorting them first
        Arrays.sort(P);
        for(int i = 0; i < K / 2; i++) {
            ans += pref[P[K - i - 1]] - (P[i] - 1 >= 0 ? pref[P[i] - 1] : 0);
            // ans %= mod;
        }
            
            
        return (int)(ans % mod);
    }
    
}