
class Solution {
    public int activitySelection(int[] nums1, int[] nums2) {
        // code here.
        int n=nums1.length;
       int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums2[i];
            arr[i][1] = nums1[i];
        }
         Arrays.sort(arr,(a, b) -> Integer.compare(a[0], b[0]));
         int finish=-1;
         int ans=0;
         for(int i=0;i<arr.length;i++){
             if(arr[i][1]>finish){
                 ans++;
                 finish=arr[i][0];
             }
         }
         return ans;
    }
}