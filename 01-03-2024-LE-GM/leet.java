class Solution {
    public String maximumOddBinaryNumber(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), ct = 0, zero = 0;
        for(int i = 0; i < n; i++)
            if(s.charAt(i) == '1')
                ct++;
        zero = n - ct;    // no of zeros

        return "1".repeat(ct - 1) + "0".repeat(zero) + "1";
    }
    
}