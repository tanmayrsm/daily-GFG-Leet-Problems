class Solution {
    public ArrayList<Integer> findMean(int[] arr, int[][] queries) {
        // code here
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 1; i < n; i++)  arr[i] += arr[i - 1];
        for(int[] q : queries) {
            int x = q[0], y = q[1];
            if(x == 0) {
                ans.add((int)(Math.floor(arr[y] / (y - x + 1))));
            } else {
                ans.add((int)(Math.floor((arr[y] - arr[x - 1]) / (y - x + 1))));
            }
        }
        return ans;
    }
}