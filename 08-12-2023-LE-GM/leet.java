class Solution {
    private static StringBuilder sb;
    public String tree2str(TreeNode root) {
        sb = new StringBuilder();
        preorder(root);
        return sb.toString();
    }
    private static void preorder(TreeNode root) {
        if(root == null)    return;
        sb.append(String.valueOf(root.val));
        if(root.left != null || (root.left == null && root.right != null)) {
            sb.append("(");
            preorder(root.left);
            sb.append(")");
        }
        if(root.right != null) {
            sb.append("(");
            preorder(root.right);
            sb.append(")");
        }
    }
}