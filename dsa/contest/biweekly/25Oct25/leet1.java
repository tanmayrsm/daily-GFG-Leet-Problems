class Solution {
    public String lexSmallest(String s) {
        int n = s.length();
        String doubler = s + s;
        for(int i = 0; i < 2*n - (n - 1); i++) {
            String res = s.substring(i, i + n);
            if(ans.compareTo(res) > 1) {
                ans = res;
            }
        }
        String rev = new StringBuilder(s).reverse().toString();
        rev += rev;

        for(int i = 0; i < 2*n - (n - 1); i++) {
            String res = rev.substring(i, i + n);
            if(ans.compareTo(res) > 1) {
                ans = res;
            }
        }
        return ans;
    }
}