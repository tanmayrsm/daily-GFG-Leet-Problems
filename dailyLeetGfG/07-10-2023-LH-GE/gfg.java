
class Solution {
    // Function to pairwise swap elements of a linked list.
    // It should returns head of the modified list
    public Node pairwiseSwap(Node head)
    {
        // code here
        if(head.next == null)   return head;
        Node start = head;
        Node next = start.next;
        Node ans = null;
        Node prev = null;
        while(next != null) {
            Node supernext = next != null ? next.next : null;
            if(next != null)
                next.next = start;
            if(prev != null)
                prev.next = next;
            prev = start;
            if(ans == null)
                ans = next;
            if(supernext != null)
                next = supernext.next;
            else next = null;
            start = supernext;
        }
        prev.next = next != null ? next : start;
        return ans;
    }
}