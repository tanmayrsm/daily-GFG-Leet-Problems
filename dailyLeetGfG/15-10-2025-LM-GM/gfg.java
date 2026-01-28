/*
class Node {
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    int pqSize;
    public int kthSmallest(Node root, int k) {
        // code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> Integer.compare(b, a));
        pqSize = 0;
        solve(root, pq, k);
        // System.out.println(pq);
        if (pq.size() < k)  return -1;
        return pq.poll();
    }
    private void solve(Node root, PriorityQueue<Integer> pq, int k) {
        if (root == null)   return;
        solve(root.left, pq, k);
        if (pqSize == k) {
            if (pq.peek() > root.data) {
                pq.poll();
                pq.offer(root.data);
            }
        } else{
            pq.offer(root.data);

            pqSize++;
        }
        solve(root.right, pq, k);
    }
}