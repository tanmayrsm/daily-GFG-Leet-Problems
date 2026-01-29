
class Solution {
    private static boolean[] primeNos;
    private static Set<Integer> pNos;
    public static ArrayList<Integer> getPrimes(int n) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        getPrimeList(n);
        for(Integer primeNo : pNos) 
            if (pNos.contains(n - primeNo)) {
                ans.add(primeNo);
                ans.add(n - primeNo);
                return ans;
            }
        ans.add(-1);
        ans.add(-1);
        return ans;

    }
    private static void getPrimeList(int n) {
        primeNos = new boolean[n + 2];
        Arrays.fill(primeNos, true);
        for(int i = 2; i * i <= n; i++) {
            if(primeNos[i])
                for(int j = i * i; j <= n; j += i)
                    primeNos[j] = false;
        }
        // store pNos in set
        pNos = new LinkedHashSet<>();
        for(int i = 2; i <= n; i++)
            if (primeNos[i])
                pNos.add(i);
    }

}