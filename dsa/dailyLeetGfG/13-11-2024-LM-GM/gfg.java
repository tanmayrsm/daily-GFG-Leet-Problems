
/* Node of a linked list
 class Node {
   int data;
    Node next;
    Node(int d)  { data = d;  next = null; }
}
 Linked List class
class LinkedList
{
    Node head;  // head of list
}*/

class Intersect {
    // Function to find intersection point in Y shaped Linked Lists.
    int intersectPoint(Node head1, Node head2) {
        // code here
        Node h1 = head1, h2 = head2;
        while(head1 != null && head2 != null) {
            if(head1 == head2)
                return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        if(head1 == null)
            head1 = h2;
        else if (head2 == null)
            head2 = h1;
        while(head1 != null && head2 != null) {
            if(head1 == head2)
                return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        if(head1 == null)
            head1 = h2;
        else if (head2 == null)
            head2 = h1;
        while(head1 != null && head2 != null) {
            if(head1 == head2)
                return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }
}