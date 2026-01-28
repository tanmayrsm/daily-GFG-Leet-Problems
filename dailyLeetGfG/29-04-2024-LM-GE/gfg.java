
/* Link list Node
class Node
{
	Node next;
	int data;
	Node(int d)
	{
		data = d;
		next = null;
	}
}*/
class Solution
{
    /*You are required to complete this method*/
    Node delete(Node head, int k)
    {
    	// Your code here
    	if(k == 1)  return null;
    	Node ans = head, prev = null;
    	int ct = 1;
    	while(head != null) {
    	    Node temp = head;
    	    head = head.next;
    	    if(ct % k == 0) {
    	        prev.next = head;
    	        prev = head;
    	    } else
        	    prev = temp;
    	    ct++;
    	}
    	return ans;
    }
}