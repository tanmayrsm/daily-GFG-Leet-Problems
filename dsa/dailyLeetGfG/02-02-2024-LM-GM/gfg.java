


class Solution
{
    int atoi(String s) {
    	// Your code here
    	int n = s.length();
        int ans = 0;
    	for(int i = 0; i < n; i++) {
    	    char c = s.charAt(i);
    	    if(i == 0 && c == '-')
                continue;
                
            int val = c - '0';
            if(!(val >= 0 && val <= 9))
                return -1;
            ans = ans*10 + val;
    	}
        return s.charAt(0) == '-' ? 0 - ans : ans;
    }
}
// -123
// 0 - ans = -1
// 1 - ans = -1
// 2 - ans = -10 + 2