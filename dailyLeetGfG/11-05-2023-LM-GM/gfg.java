
class Solution {
    public static int minimumSum(String s) {
        // code here
        char[] str = s.toCharArray();
        int n = s.length();
        int l = 0;
        int r = n - 1;
        int lastChar = -1;
        int ans = 0;
        while(l < r) {
            if(str[l] != '?' && str[r] != '?' && (str[l] != str[r]))
                return -1;
            else if (str[l] == '?' && str[r] != '?') {
                str[l] = str[r];
            } else if (str[l] != '?' && str[r] == '?') {
                str[r] = str[l];
            }
            if(str[l] != '?') {
                if(lastChar != -1)
                    ans += Math.abs((str[l] + '0') - lastChar);
                lastChar = str[l] + '0';
            }
            l++;
            r--;
        }
        return ans * 2;
    }
}
