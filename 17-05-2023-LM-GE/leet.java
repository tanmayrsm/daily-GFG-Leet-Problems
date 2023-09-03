
class Solution {
    public int pairSum(ListNode head) {
        Stack<Integer> st = new Stack<>();
        ListNode slow = null;
        ListNode fast = head;
        int ans = 0;
        while(fast != null) {   // first half iterated  (n/2 iterations)
            if(slow == null)
                slow = head;
            else    slow = slow.next;
            st.push(slow.val);
            fast = fast.next.next;
        }
        slow = slow.next;
        while(slow != null) {   // second half iteration (rest n / 2)
            ans = Math.max(slow.val + st.pop(), ans);
            slow = slow.next;
        }
        return ans;
    }
}