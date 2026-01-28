
class Solution {
    public int getMaxSum(Node root) {
        // code here
        return solve(root, 0, -1);
    }
    private int solve(Node root, int currLevel, int lastLevel) {
        if (root == null) return 0;
        int take = 0, nottake = 0;
        if (lastLevel == -1 || lastLevel + 1 < currLevel) {
            // can take
            take = root.data + solve(root.left, currLevel + 1, currLevel) + solve(root.right, currLevel + 1, currLevel);
        }
        nottake = solve(root.left, currLevel + 1, lastLevel) + solve(root.right, currLevel + 1, lastLevel);
        return Math.max(take, nottake);
    }
}