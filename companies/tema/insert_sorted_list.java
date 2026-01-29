/*
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
} */

class Solution {
    public Node sortedInsert(Node head, int data) {
        // code here
        Node newer = new Node(data);
        Node orig = head;
        Node last = head.next;
        while(last != null && last.next != orig) {
            last = last.next;
        }
        if (orig.data >= data) {
            last.next = newer;
            newer.next = orig;
            return newer;
        }

        while(head.next != orig && head.next.data < data) {
            head = head.next;
        }

        newer.next = head.next;
        head.next = newer;
        return orig;
    }
}