
//User function Template for Java
class Solution {
    private static Node prev, head;
    public Node flattenBST(Node root) {
        // Code here
        prev = null;
        head = null;
        dfs(root);
        return head;
    }
    private static void dfs(Node x) {
        if(x == null)   return;
        dfs(x.left);
        // logic... start
        if(prev != null) {
            prev.left = null;
            x.left = null;
            prev.right = x;
        }
        if(prev == null) {
            head = x;
        }
        prev = x;
        // end
        dfs(x.right);
    }
}