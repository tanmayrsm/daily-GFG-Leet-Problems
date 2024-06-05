class Solution {
    long findSwapValues(long a[], int n, long b[], int m) {
        // Your code goes here
        Arrays.sort(a);
        Arrays.sort(b);

        long sum1 = Arrays.stream(a).sum(), sum2 = Arrays.stream(b).sum();

        int i=0;
        int j=0;
        
        while(i<n && j<m)
        {
            long val1=sum1-a[i]+b[j];
            long val2=sum2-b[j]+a[i];
            
            if(val1==val2)
            return 1;
            
            if(val1>val2)
            i++;
            else
            j++;
        }
        
        return -1;

    }
}