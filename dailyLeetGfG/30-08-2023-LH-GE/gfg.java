
class Solution
{
    // returns the inorder successor of the Node x in BST (rooted at 'root')
    private static boolean got;
    private static Node ans;
	public Node inorderSuccessor(Node root,Node x)
     {
          //add code here.
          got = false;
          ans = null;
          dfs(root, x);
          return ans;
     }
    private static Node dfs(Node root, Node x) {
        if(root == null)    return null;
        Node l = dfs(root.left, x);
        if(got) {
            got = false;
            ans = root;
        }
        if(root.data == x.data) got = true;
        Node r = dfs(root.right, x);
        return root;
    }
}