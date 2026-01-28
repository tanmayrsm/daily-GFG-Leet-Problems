class Solution {
    ArrayList<ArrayList<Integer>> ans;
    public ArrayList<ArrayList<Integer>> combinationSum(int n, int k) {
        // code here
        ans = new ArrayList<ArrayList<Integer>>();
        solve(new ArrayList<>(), 0, 0, n, k, 1);
        return ans;
    }

    private void solve(ArrayList<Integer> curr, int size, int sum, int n, int k, int currNo) {
        if (size == k || sum == n || currNo == 10) {
            if (size == k && sum == n)
                ans.add(new ArrayList<>(curr));
            return;
        }

        curr.add(currNo);
        solve(curr, size + 1, sum + currNo, n, k, currNo + 1);
        curr.remove(size);
        solve(curr, size, sum, n, k, currNo + 1);

    }
}