
class Solution {
    // Function to find the vertical width of a Binary Tree.
    private static int min, max;
    public int verticalWidth(Node root) {
        // code here.
        if (root == null)   return 0;
        min = Integer.MAX_VALUE;
        max = 0;
        dfs(root, 0);
        return max - min + 1;
    }
    private static void dfs(Node root, int pos) {
        if (root == null)
            return;
        min = Math.min(min, pos);
        max = Math.max(max, pos);
        dfs(root.left, pos - 1);
        dfs(root.right, pos + 1);
    }
}
