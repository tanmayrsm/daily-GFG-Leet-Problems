class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();
        int repeated = -1;
        for(int i = 0; i <= n - 3; i++) {
            Integer x = getInt(num.charAt(i));
            Integer y = getInt(num.charAt(i + 1));
            Integer z = getInt(num.charAt(i + 2));
            if (y == x && y == z) repeated = Math.max(repeated, x);
        }
        if(repeated == -1)
            return "";

        return String.valueOf(repeated).repeat(3);

    }
    private Integer getInt(char c) {
        return (int)(c - '0');
    }
}