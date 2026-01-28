
class Solution
{
    boolean check(Node root)
    {
    	// Your code here	
    	Queue<Node> q = new LinkedList<>();
    	q.add(root);
    	boolean leafReached = false;
    	while(!q.isEmpty()) {
    	    if(leafReached) return false;
    	    int n = q.size();
    	    while(n-- > 0) {
    	        Node x = q.poll();
    	        if(x.left == null && x.right == null)  leafReached = true;
    	        if(x.left != null) {
    	            if(leafReached) return false;
    	            q.add(x.left);
    	        }
    	        if(x.right != null) {  
    	            if(leafReached) return false;
    	            q.add(x.right);
    	        }
    	    }
    	}
    	return true;
    }
}