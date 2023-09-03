//User function Template for Java
class Solution 
{ 
    boolean wifiRange(int N, String S, int X) 
    { 
        // code here
        char[] cArr = S.toCharArray();
        int[] l2r = new int[N];
        int poll = 0;
        
        for(int i = 0; i < N; i++) {    // L to R
            if(poll > 0 || cArr[i] == '1')
                l2r[i] = 1;
            if(cArr[i] == '1')
                poll = X;
            else if(poll > 0)
                poll--;
        }
        poll = 0;
        for(int i = N - 1; i >= 0; i--) {   // R to L
            if(poll > 0 || cArr[i] == '1')
                l2r[i] = 1;
            if(cArr[i] == '1')
                poll = X;
            else if(poll > 0)
                poll--;
            if(l2r[i] == 0)
                return false;
        }
        return true;
    }
} 