class GfG
{
    static List<Node> arr;
    Node buildBalancedTree(Node root) 
    {
        //Add your code here.
        arr = new ArrayList<>();
        dfs(root);
        Node newRoot = divide(0, arr.size() - 1);
        return newRoot;
    }

    private void dfs(Node root) {
        if(root == null)    return;
        dfs(root.left);
        arr.add(root);
        dfs(root.right);
    }

    private Node divide(int l, int  r) {
        if(l > r)   return null;
        int mid = (l + r) / 2;
        Node x = arr.get(mid);
        x.left = divide(l, mid - 1);
        x.right = divide(mid + 1, r);
        return x;
    }
}
