
//User function Template for Java

/* class Node
class Node
{
	Node left, right;
	int data;
	
	Node(int d)
	{
		data = d;
		left = right = null;
	}
	
}*/

//This function should return head to the DLL

class Solution
{
    private static Node prev, ans;
    //Function to convert binary tree to doubly linked list and return it.
    Node bToDLL(Node root)
    {
    	//  Your code here	
    	prev = null;
    	ans = null;
    	dfs(root);
    	return ans;
    }
    private static void dfs(Node root) {
        if (root == null)   return;
        dfs(root.left);
        if (prev == null) {
            prev = root;
            ans = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        dfs(root.right);
    }
}