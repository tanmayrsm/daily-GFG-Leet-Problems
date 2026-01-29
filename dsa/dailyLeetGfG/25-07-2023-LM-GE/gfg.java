
class Spiral
{
    //Function to return a list containing the level order 
    //traversal in spiral form.	
    ArrayList<Integer> findSpiral(Node root) 
    {
        // Your code here
        Deque<Node> dq = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        dq.addFirst(root);
        boolean lr = false;
        
        while(!dq.isEmpty()) {
            int n = dq.size();
            while(n-- > 0) {
                if (lr) {
                    Node p = dq.pollLast();
                    if(p.left != null)
                        dq.addFirst(p.left);
                    if(p.right != null)
                        dq.addFirst(p.right);
                    ans.add(p.data);
                    
                } else {
                    Node p = dq.pollFirst();
                    if(p.right != null)
                        dq.addLast(p.right);
                    if(p.left != null)
                        dq.addLast(p.left);
                    ans.add(p.data);
                }
            }
            lr = !lr;
        }
        return ans;
    }
}