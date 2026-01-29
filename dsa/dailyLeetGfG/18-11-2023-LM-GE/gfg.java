public static Node reverseDLL(Node  head) {
    //Your code here
    if(head.next == null)   return head;
    
    Node curr = head;
    while(head.next != null) {
        curr = head;
        head = head.next;
        
        Node nxt = curr.next, prev = curr.prev;
        curr.next = prev;
        curr.prev = nxt;
    }
    head.prev = null;
    head.next = curr;
    return head;
}
