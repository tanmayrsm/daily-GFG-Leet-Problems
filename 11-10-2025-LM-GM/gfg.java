class Solution {
    private int ans;
    int findMaxSum(Node root) {
        // code here
        ans = Integer.MIN_VALUE;
        solve(root);
        return ans;
    }

    private int solve(Node root) {
        if (root == null)   return 0;
        int lSum = solve(root.left);
        int rSum = solve(root.right);
        ans = Math.max(ans, root.data);

        // lets avoid negative ans
        int ret = root.data + Math.max(lSum, Math.max(rSum, lSum + rSum));
        ans = Math.max(ans, ret);

        return Math.max(0, Math.max(root.data, root.data + Math.max(lSum, rSum)));
        // send none(0) OR curr node OR curr node + max(l, r)
    }
}