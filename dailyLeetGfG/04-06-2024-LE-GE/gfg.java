
// User function Template for Java

class Solution {
    String binaryNextNumber(String s) {
        // code here.
        int n = s.length();
        char[] sRes = s.toCharArray();
        for(int i = n - 1; i >= 0; i--)
            if(sRes[i] == '1')
                sRes[i] = '0';
            else {
                sRes[i] = '1';  
                // remove leading 0s
                int r = 0;
                while(sRes[r] == '0')   r++;
                return (new String(sRes)).substring(r);
            }
        return "1" + new String(sRes);
    }
}