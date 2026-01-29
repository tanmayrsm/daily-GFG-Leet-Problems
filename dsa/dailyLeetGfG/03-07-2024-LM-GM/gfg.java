
class Solution {
    public Node removeAllDuplicates(Node head) {
        // code here
        if (head == null || head.next == null)
            return head;
        
        Node ans = null, ansHead = null, nexter = head.next;
        while (head != null) {
            boolean seq = false;
            while (nexter != null && head.data == nexter.data) {
                nexter = nexter.next;
                seq = true;
            }
            if (seq) {
                head = nexter;
                if (nexter != null)
                    nexter = nexter.next;
                if ((nexter != null && head.data != nexter.data) || nexter == null) {
                    if (ans == null)    {
                        ansHead = head;
                        ans = head;
                    } else   { 
                        ans.next = head;
                        ans = ans.next;
                    }
                }
            } else {
                if (ans == null) {
                    ansHead = head;
                    ans = head;
                } else    {
                    ans.next = head;
                    ans = ans.next;
                }
                head = nexter;
                if (nexter != null)
                    nexter = nexter.next;
            }
        }
        if (ans != null)
            ans.next = null;
        return ansHead;
    }
}