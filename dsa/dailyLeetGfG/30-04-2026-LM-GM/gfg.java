class Solution {
    public boolean isMaxHeap(int[] arr) {
        // code here
        int n = arr.length;
        for(int i=1;i<n;i++){
            int parent=(i+1)/2;
            if(arr[parent-1]<arr[i])return false;
        }
        return true;
    }
}