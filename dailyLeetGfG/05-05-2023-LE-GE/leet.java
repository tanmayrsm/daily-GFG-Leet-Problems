class Solution {
    Set<Character> vowels;
    Character arr[] = { 'a', 'e','i', 'o', 'u'};
    public int maxVowels(String s, int k) {
        vowels = new HashSet<>(Arrays.asList(arr));
        char[] S = s.toCharArray();
        int ans = 0;
        int l = 0;
        int r = k - 1;
        for(int i = 0; i < k; i++) {
            if(vowels.contains(S[i]))
                ans++;
        }
        int vowelCt = ans;
        while(r < S.length) {
            if(vowels.contains(S[l])) {
                vowelCt--;
            }
            l++;
            r++;
            if(r < S.length && vowels.contains(S[r]))
                vowelCt++;
            ans = Math.max(vowelCt, ans);
        }
        return ans;
    }
}