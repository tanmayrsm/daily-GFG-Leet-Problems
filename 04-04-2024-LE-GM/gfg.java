
//User function Template for Java

class Solution
{
    //Function to find sum of all possible substrings of the given string.
    public static long sumSubstrings(String s)
    {
        //Your code here
        long ans = (long)(s.charAt(0) - '0'), last = ans;
        int n = s.length();
        for(int i = 1; i < n; i++) {
            last = ((long)(s.charAt(i) - '0') * (i + 1) + 10 * last) % 1000000007;
            ans += last;
            ans = ans % 1000000007;
        }
        return ans;
    }
}

// 1234
// 1
// 1 + 2 + 12
// 1 + 2 + 3 + 12 + 23 + 123
// 1 + 2 + 12 + 3 + 23 + 123
// old +

// (i + 1) * num[i] + 10 * sumofDigit(i - 1)    // refferred video hint
// s[0] = 1
// s[1] = 2*2 + 10(1) = 14
// s[2] = 3*3 + 10(14) = 149