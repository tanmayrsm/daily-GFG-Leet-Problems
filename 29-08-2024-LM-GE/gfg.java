
/*

class Node
{
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
}

*/

// Function should return the length of the loop in LL.

class Solution {
    // Function to find the length of a loop in the linked list.
    public int countNodesinLoop(Node head) {
        // Add your code here.
        Node slow = head, fast = head;
        int len = 0;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)   return len;
            fast = fast.next;
            slow = slow.next;
            if (fast == slow)   break;
        }
        if (fast == null)   return len;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow and fast point to start of loop
        len++;
        fast = fast.next;
        while(fast != slow) {
            fast = fast.next;
            len++;
        }
        return len;
    }
}