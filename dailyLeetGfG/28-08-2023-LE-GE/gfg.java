
/*
class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }
*/

class GfG
{
    //Function to remove duplicates from sorted linked list.
    Node removeDuplicates(Node head)
    {
	// Your code here	    
    	Node start = head;
    	Node ans = start;
    	head = head.next;
    	while(head != null) {
    	    if(head.data == start.data) {
    	        start.next = null;
    	    }   else {start.next = head; start = start.next;}
    	    head = head.next;
    	}
    	return ans;
    }
}
