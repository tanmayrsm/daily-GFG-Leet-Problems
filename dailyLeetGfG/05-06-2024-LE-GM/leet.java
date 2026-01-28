class Solution {
    public List<String> commonChars(String[] words) {
        List<String> ans = new ArrayList<>();
        int[] fin = new int[26];
        Arrays.fill(fin, Integer.MAX_VALUE);
        for(String word : words) {
            int n = word.length();
            int[] curr = new int[26];
            for(int i = 0; i < n; i++) {
                curr[word.charAt(i) - 'a']++;
            }

            for(int i = 0; i < 26; i++)
                fin[i] = Math.min(fin[i], curr[i]);
        }
        for(int i = 0; i < 26; i++) {
            if(fin[i] != Integer.MAX_VALUE) {
                for(int j = 0; j < fin[i]; j++)
                    ans.add((char)(i + 'a') + "");
            }
        }
        return ans;
    }
}