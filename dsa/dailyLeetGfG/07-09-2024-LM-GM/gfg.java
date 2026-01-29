
// User function Template for Java

class Solution {
    //remove digit 'x' from number, and find nth no 
    long findNth(long n) {
        // code here
        String result = "";
        while(n>0)
        {
            result = (n%9) + result;
            n/=9;
            // System.out.println(result + "::" + n);
        }
        return Long.valueOf(result);
    }
}