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
    public ListNode rotateRight(ListNode head, int k) {
        int len = getLen(head);
        if(k == 0 || len <= 1 || k % len == 0)  return head;
        k = k % len;
        int same = len - k;
        ListNode s = head, prev = s;
        while(same > 0 && s.next != null) {
            prev = s;
            s = s.next;
            same--;
        }
        ListNode newstart = s;
        while(s.next != null) {
            s = s.next;
        }
        prev.next = null;
        s.next = head;
        return newstart;
    }
    private int getLen(ListNode head) {
        ListNode x = head;
        int ct = 0;
        while(x != null) {
            ct++;
            x = x.next;
        }
        return ct;
    }
}