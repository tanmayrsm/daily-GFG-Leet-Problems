class Solution {
    // Function to reverse a circular linked list
    Node reverse(Node head) {
        // code here
        if (head == null || head.next == head) return head;
        Node prev = null, curr = head;
        do {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        } while (curr != head);
        head.next = prev;
        return prev;
    }

    // Function to delete a node from the circular linked list
    Node deleteNode(Node head, int key) {
        // code here
        if (head == null || (head.next == head && head.data == key)) return null;
        if (head.data == key) {
            Node temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = head.next;
            return head.next;
        }
        Node curr = head;
        do {
            if (curr.next.data == key) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        } while (curr != head);
        return head;
    }
}