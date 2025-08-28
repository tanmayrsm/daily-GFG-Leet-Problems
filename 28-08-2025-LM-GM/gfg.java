class Solution {
    public int maxOnes(int arr[], int k) {
        // code here
        int n=arr.length;

        int l=0;
        int cnt=0;
        int maxi=0;
        for(int r=0;r<arr.length;r++){
            if(arr[r]==1){
                cnt++;
            }else if(k>0){
                cnt++;
                k--;
            }else{
                while(arr[l]==1 && l<r){
                    l++;
                    cnt--;
                }
                l++;

            }
            maxi=Math.max(cnt,maxi);
        }
        return maxi;
    }
}

arr[] = [1 0 0 1 1 0 1 0 1 1 1], k = 2


slide a  window of size k 0s, and in that window, get sum of all 1s
once u have that, then get sum of remaining 1s on left, rhs of window, if they exist


