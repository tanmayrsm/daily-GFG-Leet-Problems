
//User function Template for Java

class Solution
{
    public char nthCharacter(String s, int r, int n)
    {
        //code here
        StringBuilder sb = new StringBuilder();
        while(r-- > 0) {
            sb = new StringBuilder();
            int curr = 0, newLen = 0, m = s.length();
            while(newLen <= n && curr < m) {
                if(s.charAt(curr) == '1')
                    sb.append("10");
                else sb.append("01");
                newLen += 2;
                curr++;
            }
            s = sb.toString();
        }
        // System.out.println(s + ":;");
        return s.charAt(n);
    }
}