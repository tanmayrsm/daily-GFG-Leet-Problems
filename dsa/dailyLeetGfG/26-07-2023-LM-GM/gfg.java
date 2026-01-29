
class Solution
{
    private static int ans;
    public int kthAncestor(Node root, int k, int node)
    {
        //Write your code here
        ans = -1;
        dfs(root, node, k);
        return ans;
    }
    private static int dfs(Node root, int node, int k) {
        if(root == null)
            return -1;
        if(root.data == node)
            return 1;
        
        int l = dfs(root.left, node, k);
        if(l != -1) {
            if(l == k)
            ans = root.data;
            return l + 1;
        }
        int r = dfs(root.right, node, k);
        if(r != -1) {
            if(r == k)
                ans = root.data;
            return r + 1;
        }
        return -1;
    }
}