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
    public ListNode partition(ListNode head, int x) {
        ListNode p1 = null, p2 = null, p1Start = null, p2Start = null;
        while(head != null) {
            ListNode old = head;
            head = head.next;
            if(old.val < x) {
                if(p1 == null) { p1 = old; p1Start = p1; }
                else {
                    p1.next = old;
                    p1 = p1.next;
                }
            } else {
                if(p2 == null)  {p2 = old; p2Start = p2;}
                else {
                    p2.next = old;
                    p2 = p2.next;
                }
            }
            old.next = null;
        }
        if(p1Start != null) {
            p1.next = p2Start;
            return p1Start;
        }
        return p2Start;
    }
}