//User function Template for Java
class Solution {

    public Node sortedInsert(Node head, int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        
        Node curr = head;
        Node next = curr.next;
        
        // Case 1: Insert at the beginning
        if (data <= curr.data) {
            newNode.next = head;
            while (curr.next != head) {
                curr = curr.next;
            }
            curr.next = newNode;
            return newNode;
        }
        
        // Case 2: Insert in the middle or at the end
        while (next != head && data > next.data) {
            curr = next;
            next = next.next;
        }
        
        curr.next = newNode;
        newNode.next = next;
        
        return head;
    }
}