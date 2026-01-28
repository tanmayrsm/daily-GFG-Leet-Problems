/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head.next == null)   return true;

        // need someone to go till middle of LL
        // reverse items in second half, then compare

        // OR
        // as soon as slow ptr move, reverse node before it
        // at last start of slow half, assign new ptr
        // _<-_<-slow.........secondHalf->_->_

        ListNode fast = head, slow = head, prev = null;
        while(fast != null) {
            fast = fast.next;
            if(fast == null) {  // odd length list, ignore node at middle
                slow = slow.next;
                break;
            }
            fast = fast.next;
            
            ListNode temp = slow;
            slow = slow.next;
            if(prev == null) {
                prev = temp;
                prev.next = null;
            } else {
                temp.next = prev;
                prev = temp;
            }
        }
        // prev has pointer to reversed first half of list
        // slow has pointer to normal second half of list

        while(prev != null && slow != null) {
            if(prev.val != slow.val)  return false;
            prev = prev.next;
            slow = slow.next;
        }
        if(prev != null || slow != null)  return false;
        return true;
    }
}