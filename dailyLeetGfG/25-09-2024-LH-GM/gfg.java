
/* Structure of class Node is
class Node
{
    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}*/

class Solution {
    // Function to check whether the list is palindrome.
    boolean isPalindrome(Node head) {
        // Your code here
        Node slow = head, fast = head;
        if(head.next == null)   return true;
        while(fast != null) {
            fast = fast.next;
            if (fast.next == null) {    // even no of nodes
                fast = slow.next;
                break;
            }
            fast = fast.next;
            if (fast.next == null) {    // odd nodes
                fast = slow.next.next;
                break;
            }
            slow = slow.next;
        }
        slow.next = null;
        slow = reverse(head);

        while(slow != null && fast != null && slow.data == fast.data) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow == null && fast == null;
    }
    private Node reverse(Node head) {
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
