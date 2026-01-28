
class GfG
{
    Node mergeResult(Node node1, Node node2) {
	    // Your code here	
	    Node res = null, rStart = null;
	    while(node1 != null && node2 != null) {
	        if(node1.data <= node2.data) {
	            if(res == null) {
	                res = new Node(node1.data);
	                rStart = res;
	            }
	            else {
	                res.next = new Node(node1.data); 
	                res = res.next;
	            }
                node1 = node1.next;
	        } else {
	            if(res == null) {
	                res = new Node(node2.data); 
	                rStart = res;
	            }
	            else { 
	                res.next = new Node(node2.data);
    	            res = res.next;
	            }
	            node2 = node2.next;
	        }
	    }
	    while(node1 != null) {
            if(res == null) {
                res = new Node(node1.data);
                rStart = res;
            }
            else {
                res.next = new Node(node1.data);
	            res = res.next;
            }
            node1 = node1.next;
	    }
	    while(node2 != null) {
            if(res == null) {
                res = new Node(node2.data);
                rStart = res;
            }
            else {
                res.next = new Node(node2.data);
	            res = res.next;
            }
            node2 = node2.next;
	   }
	   return reverse(rStart);
    }
    
    private static Node reverse(Node x) {
        Node xStart = null, temp = x, prev = null;
        while(x != null) {
            x = temp;
            if(x != null) {
                temp = x.next;
                x.next = prev;
                prev = x;
            }
        }
        return prev;
    }
}
