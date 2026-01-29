
/* Node of a linked list
 class Node {
   int data;
    Node next;
    Node(int d)  { data = d;  next = null; }
}
*/

class Solution {
    int getMiddle(Node head) {
        // Your code here.
        Node slow = head, fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
            else break;
            slow = slow.next;
        }
        return slow.data;
    }
}