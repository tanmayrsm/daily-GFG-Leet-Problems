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
    public ListNode swapPairs(ListNode head) {
        if(head == null)
            return null;
        ListNode temp = head;           // curr node
        ListNode oldTemp = null;        // previous to curr node
        ListNode startPrev = null;      // start of previous reversed list
        ListNode startCurr = null;     // start of current reversed list
        int n = 0;
        ListNode ans = head;
        while(temp != null) {
            n++;
            oldTemp = temp;
            temp = temp.next;
            if(n % 2 == 0) {
                oldTemp.next = startCurr != null ? startCurr : startPrev;
                if(startCurr != null) {
                    startPrev.next = oldTemp;
                    startPrev = startCurr;
                    startPrev.next = null;
                    startCurr = null;
                }
            } else {
                if(startPrev == null) {
                    startPrev = oldTemp;
                    startPrev.next = null;
                    ans = temp;
                }
                else if (startCurr == null) {
                    startCurr = oldTemp;
                    startCurr.next = null;
                }
                if(temp == null) {
                    if(startCurr != null) {
                        startPrev.next = oldTemp;
                        startPrev = startCurr;
                        if(startPrev != null)
                            startPrev.next = null;
                        startCurr = null;
                    } else {
                        ans = startPrev;
                    }
                }
            }
        }
        return ans;
    }
}