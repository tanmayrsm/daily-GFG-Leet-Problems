class Solution {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), r = 0;
        while(r < n) {
            int next = r + 1;
            String newChar = s.charAt(r) + "";
            if(next < n && s.charAt(r) == s.charAt(next)) {
                while(next < n && s.charAt(r) == s.charAt(next)) {
                    next++;
                }
                if(next - r >= 2) {
                    sb.append(newChar + newChar);
                }
                r = next;
            } else {
                sb.append(newChar);
                r++;
            }
        }
        return sb.toString();
    }
}