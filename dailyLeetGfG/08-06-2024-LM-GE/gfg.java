/*Complete the function below*/
class Solution {
    public int findExtra(int n, int arr1[], int arr2[]) {
        // add code here.
        int l = 0, r = 0;
        while(l < n && r < n - 1) {
            if(arr1[l] != arr2[r])  return l;
            l++;    r++;
        }
        return l;
    }
}