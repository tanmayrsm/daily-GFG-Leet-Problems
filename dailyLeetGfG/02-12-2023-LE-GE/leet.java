class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] main = new int[26];   // can consider constant space
        int ans = 0;
        for(char s : chars.toCharArray())
            main[s - 'a']++;
        
        for(String d : words) {
            int[] temp = main.clone(); // copy of constant space
            boolean formed = true;
            for(char s : d.toCharArray()) {
                int idx = s - 'a';
                if(main[idx] == 0) {
                    formed = false;
                    break;
                }
                main[idx]--;
            }
            if(formed)
                ans += d.length();
            main = temp;
        }
        return ans;
    }
}