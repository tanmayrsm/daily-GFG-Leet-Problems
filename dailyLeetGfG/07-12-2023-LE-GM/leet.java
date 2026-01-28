class Solution {
    public String largestOddNumber(String num) {
        int n = num.length();
        int limit = -1;
        for(int i = n - 1; i >= 0; i--)
            if(Integer.parseInt(num.charAt(i) + "") % 2 == 1) {
                limit = i;
                break;
            }
        return limit == -1 ? "" : num.substring(0, limit + 1);
    }
}