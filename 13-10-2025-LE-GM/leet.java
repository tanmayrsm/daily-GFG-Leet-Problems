class Solution {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length, l = 0;
        List<String> ans = new ArrayList<>();
        while(l < n) {
            int r = l + 1;
            if (r < n && isAnagram(words[l], words[r])) {
                r++;
                while (r < n && isAnagram(words[l], words[r]))  r++;
                ans.add(words[l]);
                l = r;
            } else {
                ans.add(words[l]);
                l++;
            }
        }
        return ans;
    }
    private boolean isAnagram(String a, String b) {
        int n = a.length(), m = b.length();
        if (n != m) return false;
        int[] ctA = new int[26];
        int[] ctB = new int[26];
        for (int i = 0; i < n; i++) {
            ctA[a.charAt(i) - 'a']++;
            ctB[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (ctA[i] != ctB[i])
                return false;
        }
        return true;
    }
}