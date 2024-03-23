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
    public void reorderList(ListNode head) {
        if(head.next == null)   return;

        ListNode slow = head, fast = head, ans = null, headCopy = head;
        while(fast != null) {
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
            if(fast == null)
                break;
            slow = slow.next;
        }
        // slow is mid node in list
        ListNode secondHalf = slow.next;
        slow.next = null;
        ListNode revSecondHalf = reverse(secondHalf);

        // merge both lists
        boolean addFirstList = true;
        while(revSecondHalf != null && headCopy != null) {
            ListNode temp = null;
            if(addFirstList) {
                temp = headCopy;
                headCopy = headCopy.next;
            } else {
                temp = revSecondHalf;
                revSecondHalf = revSecondHalf.next;
            }
            if(ans != null)
                ans.next = temp;
            ans = temp;
            ans.next = null;
            addFirstList = !addFirstList;
        }

        ans.next = headCopy != null ? headCopy : revSecondHalf;

    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = head;
        head = head.next;
        prev.next = null;
        while(head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }
}