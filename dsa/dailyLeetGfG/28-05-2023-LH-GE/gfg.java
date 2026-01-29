
class GfG
{
    //Function to find the data of nth node from the end of a linked list.
    int getNthFromLast(Node head, int n)
    {
    	// Your code here	
    	Node f = head;
    	Node s = head;
    	Node reStart = head;
    	int size = 1;
    	while(f != null) {
    	    f = f.next;
    	    if(f != null) {
    	        size++;
    	        f = f.next;
    	        if(f != null)
    	            size++;
    	    }
    	    s = s.next;
    	}
    	if(size < n)
    	    return -1;
    	int reqdIndex = size - n;
    	int curr = 0;
    	while(reStart != null) {
    	    if(reqdIndex == curr)
    	        return reStart.data;
	        curr++;
    	    reStart = reStart.next;
    	}
    	return -1;
    }
}
