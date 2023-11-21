
class Solution
{
    //Function to check if two trees are identical.
	boolean isIdentical(Node root1, Node root2)
	{
	    // Code Here
	    return dfs(root1, root2);
	}
	private static boolean dfs(Node root1, Node root2) {
	    if(root1 == null && root2 == null)  return true;
	    if(root1 == null || root2 == null || root1.data != root2.data)  return false;   // any one of them is null
	    boolean left = dfs(root1.left, root2.left);
	    boolean right = dfs(root1.right, root2.right);
	    return left && right;
	}
	
}