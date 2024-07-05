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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int[] ans = new int[] {-1, -1};
        // first critical point
        // last critical point
        // minDis between curr and last one
        int first = -1, last = -1, lastOne = -1, n = 1;
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            prev = temp;
            if (head != null && head.next != null) {
                if ((head.val < prev.val && head.val < head.next.val) || (head.val > prev.val && head.val > head.next.val)) {
                    if (first == -1) {
                        first = n;
                        lastOne = n;
                    } else {
                        minDistance = Math.min(minDistance, n - lastOne);
                        last = n;
                        lastOne = n;
                    }
                }
            }
            n++;
        }
        if (first != -1 && last != -1) {
            ans[0] = minDistance;
            ans[1] = last - first;
        }
        return ans;
    }
}