
// User function Template for Java

class Solution {
    public String pattern(int[][] arr) {
       for(int i = 0; i < arr.length; i++){
           if(isRowPalindrome(arr[i])){
               return i + " " + "R";
           }
       }
       for(int j = 0; j < arr[0].length; j++){
           if(isColPalindrome(arr, j)){
               return j + " " + "C";
           }
       }
       return "-1";
   }
   boolean isRowPalindrome(int row[]){
       int left = 0, right = row.length - 1;
       while(left <= right){
           if(row[left] != row[right]){
               return false;
           }
           left++;
           right--;
       }
       return true;
   }
   boolean isColPalindrome(int arr[][], int j){
       int left = 0, right = arr.length - 1;
       while(left <= right){
           if(arr[left][j] != arr[right][j]){
               return false;
           }
           right--;
           left++;
       }
       return true;
   }    
}
