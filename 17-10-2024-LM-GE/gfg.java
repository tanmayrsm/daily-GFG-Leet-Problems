
// User function Template for Java
/*
struct Node
{
    int data;
    struct Node* next;

    Node(int x){
        data = x;
        next = NULL;
    }

};
*/

class Solution {
    // Function to append a new node with newData at the end of a linked list
    Node[] alternatingSplitList(Node head) {
        // code here
        Node firstHead = null, first = null, secondHead = null, second = null;
        boolean f = true;
        while(head != null) {
            Node tmp = head;
            head = head.next;
            if(f) {
                if(firstHead == null) {
                    firstHead = tmp;
                    first = tmp;
                } else {
                    first.next = tmp;
                    first = tmp;
                }
                first.next = null;
            } else {
                if(secondHead == null) {
                    secondHead = tmp;
                    second = tmp;
                } else {
                    second.next = tmp;
                    second = tmp;
                }
                second.next = null;
            }
            f = !f;
        }

        return new Node[] {firstHead, secondHead};

    }
}