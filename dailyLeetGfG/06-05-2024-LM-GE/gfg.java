
//User function Template for Java

/*  A Binary Tree nodea
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
*/
class Tree
{
    ArrayList<Integer> noSibling(Node node)
    {
        // code here
        // dfs using stack
        
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node current = node;
        while(current != null || !st.isEmpty()) {
            while(current != null) {
                st.push(current);
                current = current.left;
            }
            
            current = st.pop();
            
            // intermediate opr start
            Node res = current; 
            if (res.right == null && res.left != null)
                ans.add(res.left.data);
            if (res.left == null && res.right != null)
                ans.add(res.right.data);
            // intermediate operations end
            
            current = current.right;
            
        }
        
        Collections.sort(ans);
        if(ans.isEmpty())   ans.add(-1);
        return ans;
    }
    
}