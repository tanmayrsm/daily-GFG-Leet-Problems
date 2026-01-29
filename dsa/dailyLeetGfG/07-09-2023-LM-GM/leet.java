
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)   return head;
        ListNode prev = null, startOfPrev = head, after = null, startOfAfter = head, ans = head;
        int i = 1;

        // GET LEFT SIDE
        while(head != null) {
            if(i == left) {
                startOfAfter = prev != null ? prev.next : head;
                if(prev != null)
                    prev.next = null;
                head = head.next;
                break;
            }
            i++;
            if(prev == null)
                prev = head;
            else prev = prev.next;
            head = head.next;
        }

        // GET MID SIDE, which has to be reversed
        while(head != null) {
            if(i == right) {
                if(after != null)
                    after.next = null;
                break;
            }
            i++;
            if(after == null)   after = head;
            else after = after.next;
            head = head.next;
        }
        
        // REVERSED LL
        ListNode reversed = rev(startOfAfter);
    
        // ATTACH WITH FIRST HALF
        if(prev != null)
            prev.next = reversed;
        else ans = reversed;
        
        while(reversed != null && reversed.next != null)   {
            reversed = reversed.next;
        }
        
        // ATTACH WITH SECOND HALF
        if(reversed != null)
            reversed.next = head;

        return ans;
    }
    private static ListNode rev(ListNode head) {
        if(head == null)    return head;
        // System.out.println("head::" + head.val);
        ListNode s = null;
        while(head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = s;
            s = temp;
            // System.out.print(s.val);
        }
        return s;
    }
}