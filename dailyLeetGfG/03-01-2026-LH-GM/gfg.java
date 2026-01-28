/*
class Node {
    int data;
    Node next;
    Node bottom;

    Node(int x) {
        data = x;
        next = null;
        bottom = null;
    }
}
*/
class Solution {
    public Node flatten(Node root) {
        // code here
        Node curr = null, currHead = null;
        PriorityQueue<Node> ts = new PriorityQueue<>((Node a, Node b) -> Integer.compare(a.data, b.data));
        while (root != null) {
            ts.offer(root);
            root = root.next;
        }
        while (!ts.isEmpty()) {
            Node c = ts.poll();
            if(c.bottom != null)
                ts.offer(c.bottom);
            c.next = null;
            c.bottom = null;
            if(currHead == null) {
                currHead = c;
                curr = c;
            } else {
                curr.bottom = c;
                curr = curr.bottom;
            }
        }
        return currHead;
    }
}