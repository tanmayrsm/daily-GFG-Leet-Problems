class Solution {
    // Function to sort an array of 0s, 1s, and 2s
    public void sort012(int[] arr) {
        // code here
         // code here
        int count_zero=0;
        int count_one=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0)
            count_zero++;
            if(arr[i]==1)
            count_one++;
        }
        int count_two=arr.length-(count_zero+count_one);
        for(int i=0;i<count_zero;i++)
        {
            arr[i]=0;
        }
        for(int i=count_zero;i<count_zero+count_one;i++)
        {
            arr[i]=1;
        }
        for(int i=count_zero+count_one;i<arr.length;i++)
        {
            arr[i]=2;
        }
    }
}