
/*

Definition for doubly Link List Node
class Node
{
    int data;
    Node next;
    Node prev;
    Node(int x){
        data = x;
        next = null;
        prev = null;
    }
}
*/
class Solution {
    public Node deleteNode(Node head, int x) {
        if (x == 1) {
            Node temp = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
                temp.next = null;
            }
            return head;
        }
        Node ansHead = head, track = head;
        int curr = 1;
        while(track != null) {
            Node temp = track;
            track = track.next;
            if (x - 1 == curr) {
                temp.next = track.next;
                if (track != null && track.next != null)
                    track.next.prev = temp;
                break;
            }
            curr++;
        }
        return ansHead;
    }
}
