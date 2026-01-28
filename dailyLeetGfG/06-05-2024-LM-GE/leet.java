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
    public ListNode removeNodes(ListNode head) {
        ListNode prev = null, ans = null;
        // reverse List
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

        // apply given condition on reversed list, and finally return L->R directed list
        while(prev != null) {
            ListNode temp = prev;
            prev = prev.next;
            if(ans == null) {
                ans = temp;
                ans.next = null;
            } else if(temp.val >= ans.val) {
                temp.next = ans;
                ans = temp;
            }
        }

        return ans;

    }
}