class Solution
{ 
    //Function to convert binary tree into circular doubly linked list.
    // reff soln
    Node head;
    Node prev;
    Node bTreeToClist(Node root)
    {
        prev=null;
        head=null;
        dfs(root);
        // System.out.println("head :;" + head.data + " ;; " + prev.data);
        head.left=prev;
        prev.right=head;
        return head;
    }
    void dfs(Node root)
    {
        if(root!=null)
        {
            dfs(root.left);
            if(prev!=null)
            {
                prev.right=root;
                root.left=prev;
            }
            else
                head=root;
            prev=root;
            dfs(root.right);
        }
    }
}
    
