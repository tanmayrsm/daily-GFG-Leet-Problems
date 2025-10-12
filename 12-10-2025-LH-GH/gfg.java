class Solution {
    int moves;

    public int distCandy(Node root) {
        moves = 0;
        dfs(root);
        return moves;
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        // Add absolute transfers (each transfer counts as 1 move)
        moves += Math.abs(left) + Math.abs(right);

        // Return net balance to parent
        return node.data + left + right - 1;
    }
}