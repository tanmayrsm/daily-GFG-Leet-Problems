class Solution{ // reff soln
    
    static long countSubarrays(int a[], int n, int l, int r)  
    { 
        // Complete the function
        long count = 0, pre = 0, preLow = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] < l) {
                count += pre;
                preLow++;
            } else if(a[i] > r) 
                pre = preLow = 0;
            else {
                count += (pre + preLow + 1);
                pre += preLow + 1;
                preLow = 0;
            }
        }
        
        return count;
    } 
}