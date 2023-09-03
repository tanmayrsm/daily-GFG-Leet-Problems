
class Sol
{
	public static Node findUnion(Node head1,Node head2)
	{
	    //Add your code here.
	    Node ans = null;
	    Node returnAns = null;
	    
	    TreeSet<Integer> t1 = new TreeSet<>();
	    
	    while(head1 != null) {
	        t1.add(head1.data);
	        head1 = head1.next;
	    }
	    while(head2 != null) {
	        t1.add(head2.data);
	        head2 = head2.next;
	    }
	    for(Integer r : t1) {
	        if(ans == null) {
	            ans = new Node(r);
	            returnAns = ans;
	        } else {
	            ans.next = new Node(r);
	            ans = ans.next;
	        }
	    }
	    return returnAns;
	}
}