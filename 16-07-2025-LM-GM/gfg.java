class Solution {
    public static int countNumbers(int n) {
        // code here
        int ans = 0;
        for(int i=6;i*i<=n;i++) {
            int x = i*i,cnt = 1;
            for(int j=2;j*j<x;j++) {
                if(x%j==0) cnt++;
                if(cnt>4) break;
            }
            if(cnt==4) ans++;
        }
        return ans;
    }
}

/*
36 -> 1,2,3,4,6,9,12,18,36

36,100,196
6,10,16,20
 */







