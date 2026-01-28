/*
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
*/

class Solution {
    Node mergeKLists(Node[] arr) {
        // code here
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> Integer.compare(a.data, b.data));
        Node ans = null, head = null;
        for (Node a : arr)
            pq.offer(a);
        while(!pq.isEmpty()) {
            Node top = pq.poll();
            if (top.next != null) {
                pq.offer(top.next);
            }
            if (ans == null) {
                ans = top;
                head = ans;
            } else {
                ans.next = top;
                ans = ans.next;
            }
        }
        return head;
    }
}