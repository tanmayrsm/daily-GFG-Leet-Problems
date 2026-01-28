/*
class Node{
    int data;
    Node next;
    Node(int a){  data = a; next = null; }
}*/

class Solution {
    // Function to count nodes of a linked list.
    public int getCount(Node head) {
        // code here
        int no = 0;
        while(head != null) {
            head = head.next;
            no++;
        }
        return no;
    }
}