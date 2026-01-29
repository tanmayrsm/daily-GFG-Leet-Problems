class Solution {
    public static int[] productExceptSelf(int arr[]) {
        // code here
        long prod = 1;
        int n = arr.length, zeroCt = 0;
        int[] ans = new int[n];
        for (int x : arr) {
            if (x != 0)
                prod *= x;
            else if (x == 0) {
                zeroCt++;
                if (zeroCt > 1)
                    return ans;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (zeroCt == 1 && arr[i] == 0) {
                ans[i] = (int) (prod);
            }
            else if (arr[i] != 0 && zeroCt == 0) {
                ans[i] = (int)(prod / arr[i]);    
            }
        }
        return ans;
    }
}