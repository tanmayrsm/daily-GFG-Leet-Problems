
class Solution
{
    //Function to sort a linked list of 0s, 1s and 2s.
    static Node segregate(Node head)
    {
        // add your code here
        Node zero = null;
        Node zeroNext = zero;
        Node ones = null;
        Node oneNext = ones;
        Node twos = null;
        Node twoNext = twos;
        Node ans = null;
        
        while(head != null) {
            if(head.data == 0) {
                if(zero == null) {
                    zero = new Node(head.data);
                    zeroNext = zero;
                } else {
                    zeroNext.next = new Node(head.data);
                    zeroNext = zeroNext.next;
                }
            }
            if(head.data == 1) {
                if(ones == null) {
                    ones = new Node(head.data);
                    oneNext = ones;
                } else {
                    oneNext.next = new Node(head.data);
                    oneNext = oneNext.next;
                }
            }
            if(head.data == 2) {
                if(twos == null) {
                    twos = new Node(head.data);
                    twoNext = twos;
                } else {
                    twoNext.next = new Node(head.data);
                    twoNext = twoNext.next;
                }
            }
            head = head.next;
        }
        
        if(zeroNext != null) {
            zeroNext.next = ones != null ? ones : twos;
        }
        if(oneNext != null) {
            oneNext.next = twos;
        }
        return zero != null ? zero : (ones != null ? ones : twos);
        
    }
}

