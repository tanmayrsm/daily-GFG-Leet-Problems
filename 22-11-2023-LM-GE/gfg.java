class GfG
{
    // return true/false denoting whether the tree is Symmetric or not
    public static boolean isSymmetric(Node root)
    {
        // add your code here;
        if(root == null)    return true;
        return dfs(root.left, root.right);
    }
    static boolean dfs(Node root1, Node root2) {
        if(root1 == null && root2 == null)  return true;
        if((root1 == null && root2 != null) || (root1 != null && root2 == null))    return false;
        if(root1.data != root2.data)    return false;
        boolean l = dfs(root1.left, root2.right);
        boolean r = dfs(root1.right, root2.left);
        return l && r;
    }
}