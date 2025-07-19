class Solution {
    public int vowelCount(String s) {
        // code here
        int n = s.length(), totLen = 0, mulFact = 1;
        int[] ct = new int[26];
        Set<Character> perm = Set.of('a','e','i','o','u');

        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            ct[curr - 'a']++;
        }
        for (int i = 0; i < ct.length; i++) {
            char curr = (char)(i + 'a');
            if (perm.contains(curr) && ct[i] > 0) {
                totLen++;
                mulFact *= ct[i];
            }
        }
        if (totLen == 0)    return 0;
        totLen = fact(totLen);
        return totLen * mulFact;
        // aaioo
        // aio -> 3!
        // aio(dusra a) -> 3!
        // with other o -> 3! + 3!
    }
    private int fact(int n) {
        int res = 1;
        while(n > 1) {
            res *= n;
            n--;
        }
        return res;
    }
}