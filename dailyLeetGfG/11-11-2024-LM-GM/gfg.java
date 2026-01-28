class Solution {
    public int minIncrements(int[] arr) {
        // Code here
        Arrays.sort(arr);
        int curr = -1, ans = 0;
        for(int no : arr) {
            if(no > curr) {
                curr = no;
            } else {
                ans += Math.abs(no - (curr + 1));
                curr++;
            }
        }
        return ans;
    }
}