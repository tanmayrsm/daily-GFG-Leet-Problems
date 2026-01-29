
class Solution {

    // Return the sum of last k nodes
    public int sumOfLastN_Nodes(Node head, int n) {
        // write code here
        Node rev = reverse(head);
        int sum = 0;
        while(rev != null && n-- > 0) {
            sum += rev.data;
            rev = rev.next;
        }
        return sum;
    }
    
    private Node reverse(Node head) {
        Node prev = null;
        while(head != null) {
            Node temp = head;
            head = head.next;
            if(prev == null) {
                prev = temp;
            } else {
                temp.next = prev;
                prev  = temp;
            }
        }
        return prev;
    }
}