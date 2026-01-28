class Solution {
    public char findKthBit(int n, int k) {
        StringBuilder sb = new StringBuilder("0");
        int currLen = 1;
        k--;
        for(int i = 2; i <= n; i++) {
            String post1 = invertAndReverse(sb); 
            sb.append("1");
            sb.append(post1);
            currLen *= 2;
            currLen++;
            if(k < currLen)
                return sb.charAt(k);
        }
        return sb.charAt(k);
    }
    private String invertAndReverse(StringBuilder sb) {
        StringBuilder newer = new StringBuilder("");
        int n = sb.length();
        for(int i = 0; i < n; i++) {
            if(sb.charAt(i) == '0')
                newer.append("1");
            else newer.append("0");
        }
        newer.reverse();
        return newer.toString();
    }
}