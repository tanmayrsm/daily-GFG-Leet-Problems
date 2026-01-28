class Solution {
    public int prefixCount(String[] words, String pref) {
        Arrays.sort(words);
        int l = 0, r = words.length - 1, n = pref.length();
        for (int i = 0; i < n; i++) {
            // while (l < n && words[l].length() < n)  l++;
            while(l <= r && (words[l].length() < n || words[l].charAt(i) != pref.charAt(i))) l++;

            // while (r >= 0 && words[r].length() < n)  r--;
            while(l <= r && (words[r].length() < n || words[r].charAt(i) != pref.charAt(i))) r--;
            if (l > r)  return 0;
        }
        return r - l + 1;
            
    }
}