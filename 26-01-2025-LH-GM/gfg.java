
class Solution {
    // Function to remove a loop in the linked list.
    public static void removeLoop(Node head) {
        // code here
        Node fast = head, slow = head, prev = null;
        while(fast != null && slow != null) {
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
            }
            prev = slow;
            slow = slow.next;
            if(fast == slow)    // loop present    
                break;
        }
        if(fast == null || slow == null)    // no loop present
            return;
        // loop present
        fast = head;
        // point of intersection of slow and fast ptr would be node where cycle starts
        while(fast != slow) {   
            prev = slow;
            fast = fast.next;
            slow = slow.next;
        }
        if(prev != null)
            prev.next = null;
    }
}