class Solution {
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int m = word.length(), ct = 1;
        for(int i = 1; i <= m; i++) {
            if(ct < 9 && i < m && word.charAt(i) == word.charAt(i - 1))
                ct++;
            else {
                sb.append(String.valueOf(ct) + (word.charAt(i - 1) + ""));
                ct = 1;
            }
        }
        return sb.toString();
    }
}