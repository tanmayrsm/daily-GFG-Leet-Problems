
class BST
{   
    //Function to find the lowest common ancestor in a BST. 
	Node LCA(Node root, int n1, int n2)
	{
        // code here.
        int N1 = Math.min(n1, n2);
        int N2 = Math.max(n1, n2);
        
        if(root.data < N1) {
            return LCA(root.right, N1, N2);
        } else if (root.data > N2) {
            return LCA(root.left, N1, N2);
        } else  return root;
    }
    
}
