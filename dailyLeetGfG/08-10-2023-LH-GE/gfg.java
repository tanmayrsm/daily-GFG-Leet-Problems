
// sortedInsert method should return the head of the modified linked list.
class Solution {
    Node sortedInsert(Node head1, int key) {
        // Add your code here.
        Node ins = new Node(key);
        if(head1.data >= ins.data)  {
            ins.next = head1;
            return ins;
        }
        Node ans = head1;
        while(head1.next != null) {
            if(head1.next != null && head1.next.data >= key) {
                ins.next = head1.next;
                head1.next = ins;
                return ans;
            }
            head1 = head1.next;
        }
        head1.next = ins;
        return ans;
    }
}