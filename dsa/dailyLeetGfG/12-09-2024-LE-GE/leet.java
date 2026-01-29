class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int[] valid = new int[26];
        Arrays.fill(valid, -1);
        int n = allowed.length(), ans = 0;
        for (int i = 0; i < n; i++)
            valid[allowed.charAt(i) - 'a']++;
        for (String word : words) {
            n = word.length();
            boolean isThere = true;
            for (int i = 0; i < n; i++)
                if (valid[word.charAt(i) - 'a'] == -1) {
                    isThere = false;
                    break;
                }
            if (isThere)    ans++;
        }
        return ans;
    }
}