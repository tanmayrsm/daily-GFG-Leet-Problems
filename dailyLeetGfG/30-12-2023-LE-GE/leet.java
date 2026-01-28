class Solution {
    // LOGIC : each charater in whole array, must be divisible by words.length
    // so we can somehow arrange it among 'n' instances of 'words' array
    public boolean makeEqual(String[] words) {
        int[] chars = new int[26];
        int reqCt = words.length;
        for(String word : words) {
            int n = word.length();
            for(int i = 0;  i < n; i++)
                chars[word.charAt(i) - 'a']++;
        }
        for(int x : chars) {
            if(x > 0 && x % reqCt != 0) return false;
        }
        return true;
    }
}