/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null)    return head;
        // using Map DS is only choice, as it will somehow store reference of old nodes and new nodes addresses

        Map<Node, Node> store = new HashMap<>();
        Node repeat = head; // for tracing random ptr from start again later
        Node newer = new Node(head.val);    // for tracing the head, and storing new nodes
        Node ans = newer;   // for returning ans
        Node repeatAns = ans;   // for tracing random pointer from start later
        
        store.put(head, newer);
        head = head.next;

        // build new nodes using 'next' pointers
        while(head != null) {
            Node n = new Node(head.val);
            store.put(head, n);
            newer.next = n;
            newer = newer.next;
            head = head.next;
        }

        while(repeat != null) {
            repeatAns.random = repeat.random == null ? null : store.get(repeat.random);
            repeatAns = repeatAns.next;
            repeat = repeat.next;
        }

        return ans;
    }
}