
class Solution {
    /*you are required to complete this function */
    boolean hasPathSum(Node root, int target) {
        // Your code here
        return dfs(root, 0, target);
    }
    private static boolean dfs (Node root, int currSum, int target) {
        if (root.left == null && root.right == null) {
            currSum += root.data; 
            return currSum == target;
        }
        boolean l = false, r = false;
        if (root.left != null)
            l = dfs(root.left, root.data + currSum, target);
        if (root.right != null)
            r = dfs(root.right, root.data + currSum, target);
        return l || r;
        
    }
}