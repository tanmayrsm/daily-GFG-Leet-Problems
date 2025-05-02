class Solution {
    public int findMaximum(int[] arr) {
        // code here
        for(int i=1;i<arr.length;i++){
         if(arr[i]<arr[i-1] && i<=arr.length-1){
             return arr[i-1];
         }
     }
     return arr[arr.length-1];
    }
}