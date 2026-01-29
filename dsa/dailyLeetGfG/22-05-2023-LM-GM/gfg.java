
class Solution {
    public static int solve(int n, int[] p) {
        // code here
        // in short, we need to remove internal nodes (nodes except root and leaf)
        // hence count the leaf nodes
        int ans = n - 1;
        boolean[] nonLeafList = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(p[i] != -1) {
                nonLeafList[p[i]] = true;
            }
        }
        for(int i = 1; i < n; i++) {
            if(!nonLeafList[i]) // a leafz
                ans--;
        }
        return ans;
    }
}
        
