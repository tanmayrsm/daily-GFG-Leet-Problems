
class Solution {
    public String convertToTitle(int col) {
        StringBuilder ans = new StringBuilder("");
        while(col > 0) {
            if(col % 26 == 0) {
                ans.append("Z");
                col--;  // so next time 25/26 = 0, automatically stops loop
            } else {
                int j = col % 26 + 64;  // 1 + 64 == 'A'...and so on
                char c = (char)j;
                ans.append("" + c);
            }
            col = col / 26;
        }
        return ans.reverse().toString();
    }
}