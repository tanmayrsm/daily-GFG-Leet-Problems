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
class Solution {    // referred soln
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] list=new ListNode[k];
        ListNode curr=head;
        int len=0;
        while(curr!=null)
        {
            len+=1;
            curr=curr.next;
        }

        curr=head;
        int index=0;
        int mainLen=len/k;
        int extra=len%k;
        while(curr!=null){
            list[index++]=curr;
            int cnt= mainLen - 1 + ((extra-- > 0) ? 1 : 0);
            for (int i = 0; i < cnt ; i++)
                curr = curr.next;
            ListNode temp = curr.next;
            curr.next = null;
            curr = temp;
            
        }
        return list;
    }
}