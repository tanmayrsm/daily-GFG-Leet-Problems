class Solution {
    long totalSum;
    long ans;
    public int maxProduct(TreeNode root) {
        totalSum = dfsSum(root);
        ans = -1;
        maxSum(root);
        return (int)(ans % 1_000_000_007);
    }
    private long maxSum(TreeNode root) {
        if (root == null)   return -1;
        long left = maxSum(root.left);
        long right = maxSum(root.right);
        long leftBroken = (left == -1) ? -1 : (totalSum - left);
        long rightBroken = (right == -1) ? -1 : (totalSum - right);
        if (leftBroken != -1 && leftBroken * left > ans)   ans = leftBroken * left;
        if (rightBroken != -1 && rightBroken * right >  ans) ans = rightBroken * right;
        return root.val + (left == -1 ? 0 : left) + (right == -1 ? 0 : right);
    }
    private long dfsSum(TreeNode root) {
        if(root == null)    return 0;
        return root.val  + dfsSum(root.left) + dfsSum(root.right);
    }
}
