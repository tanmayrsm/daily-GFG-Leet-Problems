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
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> existing = new HashSet<>();
        for (int num : nums)    existing.add(num);
        ListNode ans = null, ansHead = null;
        while(head != null) {
            ListNode curr = head;
            head = head.next;
            if (!existing.contains(curr.val)) {
                if(ansHead == null) {
                    ansHead = curr;
                    ans = curr;
                } else {
                    ans.next = curr;
                    ans = ans.next;
                }
                ans.next = null;
            }
        }
        return ansHead;
    }
}