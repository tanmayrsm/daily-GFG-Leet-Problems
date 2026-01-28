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
    public ListNode doubleIt(ListNode head) {
        // reverse list
        ListNode prev = reverse(head), prevHead = prev;
        ListNode p = null;

        int carry = 0;
        while (prev != null) {
            p = prev;
            int curr = carry + prev.val * 2;
            carry = curr / 10;
            prev.val = curr % 10;
            prev = prev.next;
        }
        if(carry > 0) {
            ListNode ans = new ListNode(carry);
            p.next = ans;
        }

        // return reversed list
        return reverse(prevHead);
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode temp = head;
            head = head.next;
            if(prev == null) {
                prev = temp;
                prev.next = null;
            } else {
                temp.next = prev;
                prev = temp;
            }
        }
        return prev;
    }
}