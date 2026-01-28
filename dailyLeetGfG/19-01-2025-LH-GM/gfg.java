class Solution {
    public Node rotate(Node head, int k) {
        // add code here
        int n = getLen(head), ct = 1;
        k = k % n;
        if(k == 0 || head.next == null)  return head;
        Node oldHead = head, newHead = null;
        while(ct < k) {
            ct++;
            head = head.next;
        }
        newHead = head.next;
        head.next = null;
        Node temp = newHead;
        while(temp.next != null)
            temp = temp.next;
        temp.next = oldHead;
        return newHead;
    }
    private int getLen(Node head) {
        int ct = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            ct++;
        }
        return ct;
    }
}