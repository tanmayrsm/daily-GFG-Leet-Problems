

class Solution {
    
    public int countSubArrayProductLessThanK(long a[], int n, long k)
    {
        int ct = 0;
        long currAns = 1;
        int l = 0;
        int r = 0;
        
        for(int i = 0; i < n; i++) {
            if(a[i] >= k) {
                l = i + 1;
                currAns = 1;
            } else {
                while(currAns * a[i] >= k && l < i) {
                    currAns /= a[l++];
                }
                currAns *= a[i];
                if(currAns >= k) {
                    currAns = 1;
                } else
                    ct += 1 + (i - l);
            }
        }
        
        return ct;
    }
}