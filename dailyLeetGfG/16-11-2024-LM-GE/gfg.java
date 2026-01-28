
class Solution {
    void pushZerosToEnd(int[] arr) {
        // code here
        // code here
        int idx=0,zeroes=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0)
            {
                zeroes++;
            }
            else
            {
                arr[idx++]=arr[i];
            }
        }
        while(zeroes-- > 0)
        {
            arr[idx++]=0;
        }
    }
}