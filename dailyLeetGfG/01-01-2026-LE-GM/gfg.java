/*
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
*/

class Solution {
    public Node intersectPoint(Node head1, Node head2) {
        // code here
        Node f = head1, s = head2, f1 = head1, s1 = head2;
        while(f != null && s != null) {
            f = f.next;
            s = s.next;
            if( f == s) return f;
        }
        if(f == null) {
            f = s1;
        }
        else s = f1;

        // rep 2
        while(f != null && s != null) {
            f = f.next;
            s = s.next;
            if( f == s) return f;
        }
        if(f == null) {
            f = s1;
        }
        else s = f1;

        // rep 3
        while(f != null && s != null) {
            f = f.next;
            s = s.next;
            if( f == s) return f;
        }
        return null;

    }
}