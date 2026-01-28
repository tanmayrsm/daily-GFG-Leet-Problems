/*
class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}
*/

class Solution {
    public Node addOne(Node head) {
        // code here.
        Node ans = null;
        head = reverse(head);
        int carry = 0, adder = 1;
        while (head != null) {
            int newNum = adder + head.data + carry;
            if (newNum > 9) {
                carry = 1;
                newNum = 0;
            } else carry = 0;
            adder = 0;
            head.data = newNum;
            if (ans == null)
                ans = head;
            if (carry == 1 && head.next == null) {
                head.next = new Node (1);
                head = head.next;
            }
            head = head.next;
        }
        return reverse(ans);
    }
    
    private static Node reverse(Node head) {
        Node prev = null;
        while(head != null) {
            Node temp = head;
            head = head.next;
            
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }
    
}