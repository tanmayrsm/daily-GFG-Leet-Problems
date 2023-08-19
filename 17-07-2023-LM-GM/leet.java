class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = rev(l1);   // reverse list to start from rightmost digit
        l2 = rev(l2);   // reverse list to start from rightmost digit
        int carry = 0;
        ListNode ans = null;

        while(l1 != null && l2 != null) {
            ListNode g = new ListNode((l1.val + l2.val + carry) % 10);
            if(l1.val + l2.val + carry >= 10) {
                carry = 1;
            } else  carry = 0;   
            if(ans == null) {
                ans = g;
            } else {
                g.next = ans;
                ans = g;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            ListNode g = new ListNode((l1.val + carry) % 10);
            if(l1.val + carry >= 10) {
                carry = 1;
            } else  carry = 0;
            g.next = ans;
            ans = g;
            l1 = l1.next;
        }

        while(l2 != null) {
            ListNode g = new ListNode((l2.val + carry) % 10);
            if(l2.val + carry >= 10) {
                carry = 1;
            } else  carry = 0;
            g.next = ans;
            ans = g;
            l2 = l2.next;
        }

        if(carry != 0) {
            ListNode g = new ListNode(carry);
            g.next = ans;
            ans = g;
        }
        return ans;
    }
    private static ListNode rev(ListNode n) {
        ListNode start = n;
        n = n.next;
        start.next = null;
        while(n != null) {
            ListNode next = n.next;
            n.next = start;
            start = n;
            n = next;
        }
        return start;
    }
}