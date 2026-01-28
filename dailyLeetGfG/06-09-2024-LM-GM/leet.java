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
        Set<Integer> st = new HashSet<>();
        for (int num : nums)    st.add(num);
        ListNode prev = null, ansHead = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            if (!st.contains(temp.val)) {
                if (ansHead == null) {
                    ansHead = temp;
                }
                if (prev == null)
                    prev = temp;
                else {
                    prev.next = temp;
                    prev = prev.next;
                }   
            } else if (prev != null)
                    prev.next = null;
        }
        return ansHead;
    }
}