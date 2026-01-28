/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // slow and fast pointer approach
        ListNode slow = head, fast = head;
        while(fast != null && slow != null) {
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
            slow = slow.next;
            if(slow == fast && fast != null)
                return true;
        }
        return false;
    }
}