
// User function Template for Java

class Solution {
    private static Set<List<Integer>> ans;
    public List<List<Integer>> CombinationSum2(int arr[], int n, int k) {
        // Code Here.
        ans = new LinkedHashSet<>();
        Arrays.sort(arr);
        solve(arr, 0, k, new ArrayList<>(), 0);
        return new ArrayList<>(ans);
    }
    private static void solve(int[] arr, int curr, int currK, List<Integer> currList, int currSize) {
        if(currK == 0) {
            ans.add(new ArrayList<>(currList));
            return;
        }
        if(curr == arr.length)  return;
        if(currK - arr[curr] >= 0) {    // take
            currList.add(arr[curr]);
            solve(arr, curr + 1, currK - arr[curr], currList, currSize + 1);
            currList.remove(currSize);
        }
        solve(arr, curr + 1, currK, currList, currSize);    // not take
    }
}