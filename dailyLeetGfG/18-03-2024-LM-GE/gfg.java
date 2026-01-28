class Solution
{
    //Function to return the level order traversal of a tree.
    static ArrayList <Integer> levelOrder(Node root) 
    {
        // Your code here
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            Node x = q.poll();
            ans.add(x.data);
            if(x.left != null)
                q.offer(x.left);
            if(x.right != null)
                q.offer(x.right);
        }
        return ans;
    }
}