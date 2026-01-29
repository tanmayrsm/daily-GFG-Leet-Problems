class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), k = 0;
        for(int i = 0; i < n; ) {
            if(k < spaces.length && i == spaces[k]) {
                sb.append(" ");
                k++;
            }
            else {
                sb.append(s.charAt(i) + "");
                i++;
            }
        }
        return sb.toString();
    }
}