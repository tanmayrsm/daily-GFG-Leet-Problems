
class Solution {
    Node reverseList(Node head) {
        // code here
        Node curr = head, prev = null;
        while(head != null) {
            Node temp = head;
            head = head.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }
}
