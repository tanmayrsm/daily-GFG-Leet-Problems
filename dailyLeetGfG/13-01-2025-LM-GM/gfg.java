class Solution {

    public int maxWater(int arr[]) {
        // Code Here
        int n=arr.length;
        int left=0, right=n-1, ans=0;
        while(left<right){
            if(arr[left]<=arr[right]){
                ans = Math.max(ans, arr[left]*(right-left));
                left++;
            }else{
                ans = Math.max(ans, arr[right]*(right-left));
                right--;
            }
        }
        return ans;
        
    }
}