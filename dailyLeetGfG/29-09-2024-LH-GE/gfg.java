
class Solution {
    int totalCount(int k, int[] arr) {
        // code here
        int ans = 0;
        for(int no : arr) {
            int parts = no / k + (no % k != 0 ? 1 : 0);
            ans += parts;
        }
        return ans;
    }
}