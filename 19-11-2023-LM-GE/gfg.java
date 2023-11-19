
class Solution
{
   public static Node findIntersection(Node head1, Node head2)
    {
        // code here.
        Node ansHead = null, ans = null;
        
        while(head1 != null && head2 != null) {
            if(head1.data == head2.data) {
                Node newer = new Node(head1.data);
                if(ansHead == null) {
                    ansHead = newer;
                    ans = ansHead;
                } else {
                    ans.next = newer;
                    ans = ans.next;
                }
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.data < head2.data)
                head1 = head1.next;
            else head2 = head2.next;
        }
        
        
        return ansHead;
    }
}