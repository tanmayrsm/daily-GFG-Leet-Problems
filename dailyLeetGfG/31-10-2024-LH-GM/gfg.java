/*class of the node of the DLL is as
/*
class Node {
    int data;
    Node prev, next;
    Node(int data) {
        this.data = data;
        this.prev = this.next = null;
    }
}
*/
class Solution {
    public Node sortedInsert(Node head, int x) {
        // add your code here
        Node newer = new Node(x);
        if(x <= head.data) {
            newer.next = head;
            return newer;
        }
        Node ans = head;
        while(head != null) {
            Node temp = head;
            head = head.next;
            if(head != null && head.data >= x) {
                temp.next = newer;
                newer.next = head;
                return ans;
            } else if (head == null) {
                temp.next = newer;
            }
        }
        return ans;
        
    }
}