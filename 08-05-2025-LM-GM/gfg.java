
class Solution {
    public int findMissing(int[] arr) {
        int a = arr[0];
        int d = arr[1]-arr[0];
        int n = arr.length;
        int low = 0;
        int high = n-1;
        
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid] == a+d*mid){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return a+d*low;
    }
}