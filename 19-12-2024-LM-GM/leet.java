class Solution {
    public int maxChunksToSorted(int[] arr) {
        int sum = 0, ans = 0;
        for(int i = 0 ; i < arr.length; i++){
            sum += arr[i];
            if(sum == (i*(i+1))/2)
                ans++;
        }

        return ans;
    }
}