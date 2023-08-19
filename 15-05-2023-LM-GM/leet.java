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
    public ListNode swapNodes(ListNode head, int k) {
        ListNode frontTracer = head;
        ListNode backTracer = head;
        ListNode fromLeft = head;
        ListNode fromRight = head;
        int n = 1;
        while(backTracer != null) {
            if(n == k) {
                fromLeft = backTracer;
            }
            backTracer = backTracer.next;
            n++;
        }
        n--;
        int r = 1;
        while(frontTracer != null) {
            if(r == n - k + 1)
                fromRight = frontTracer;
            frontTracer = frontTracer.next;
            r++;
        }
        int temp = fromLeft.val;
        fromLeft.val = fromRight.val;
        fromRight.val = temp;
        return head;
    }
}