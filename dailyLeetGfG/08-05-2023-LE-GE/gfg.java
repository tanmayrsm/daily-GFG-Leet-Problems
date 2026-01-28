
class Solution{
	int modulo(String s, int m) {
		//Write your code here
		int n = s.length();
		int[] pwrTwo = new int[n];
		pwrTwo[0] = 1 % m;
		for(int i = 1; i < n; i++)  {
		    pwrTwo[i] = pwrTwo[i - 1] * (2 % m);
		    pwrTwo[i] %= m;
		}
	    int i = 0;
	    int j = n - 1;
	    int res = 0;
	    while(i < n) {
	        if(s.charAt(j) == '1') {
	           res += pwrTwo[i];
	           res %= m;
	        }
	        i++;
	        j--;
	    }
	    return res;
	}
}