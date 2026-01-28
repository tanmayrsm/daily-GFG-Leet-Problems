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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode r = head; 
        int N = 0;
        while(r != null) {
            r = r.next;
            N++;
        }
        n = N - n + 1;
        if(n == 1)  return head.next;
        ListNode prev = null, start = head;
        int ct = 1;
        while(ct < n) {
            prev = head;
            head = head.next;
            ct++;
        }
        prev.next = head.next;
        head.next = null;
        return start;
    }
}