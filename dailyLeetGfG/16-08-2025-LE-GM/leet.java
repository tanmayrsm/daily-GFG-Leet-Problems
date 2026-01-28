class Solution {
    public int maximum69Number (int num) {
        String no = String.valueOf(num);
        char[] ans = no.toCharArray();
        for(int i = 0; i < no.length(); i++) {
            if(no.charAt(i) == '6') {
                ans[i] = '9';
                return Integer.parseInt(String.valueOf(ans));
            }
        }
        return Integer.parseInt(String.valueOf(ans));
    }
}