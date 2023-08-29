class Solution
{
    Node compute(Node head)
    {
        // your code here
        Node start = head;
        head = head.next;
        if(head == null)    return start;
        start.next = null;
        
        while(head != null) {
            Node currHead = head;
            head = head.next;
            if(currHead.data <= start.data) {
                currHead.next = start;
            } else {
                while(start != null && start.data < currHead.data) {
                    Node temp = start;
                    start = start.next;
                    temp.next = null;
                }
                currHead.next = start;
            }
            start = currHead;
        }
        
        return rev(start);
    }
    
    private static Node rev(Node start) {
        Node head = start.next;
        start.next = null;
        while(head != null) {
            Node currHead = head;
            head = head.next;
            currHead.next = start;
            start = currHead;
        }
        return start;
    }
}