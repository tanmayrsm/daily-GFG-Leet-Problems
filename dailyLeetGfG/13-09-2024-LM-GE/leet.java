class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] ans = new int[queries.length];
        int k = 0;
        for (int i = 1; i < arr.length; i++)
            arr[i] = arr[i - 1] ^ arr[i];
        for (int[] query : queries) {
            int start = query[0], end = query[1];
            ans[k++] = start == 0 ? arr[end] : arr[end] ^ arr[start - 1];
        }
        return ans;
    }
}