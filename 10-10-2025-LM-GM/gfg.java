/*
class Node {
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}
*/

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        // code here
        Deque<Node> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        q.offerLast(root);
        boolean odd = true;
        while (!q.isEmpty()) {
            int m = q.size();
            while(m-- > 0) {
                if (odd) {
                    Node curr = q.pollLast();
                    ans.add(curr.data);
                    if (curr.left != null)
                        q.offerFirst(curr.left);
                    if (curr.right != null)
                        q.offerFirst(curr.right);
                } else {
                    Node curr = q.pollFirst();
                    ans.add(curr.data);
                    if (curr.right != null)
                        q.offerLast(curr.right);
                    if (curr.left != null)
                        q.offerLast(curr.left);
                }
            }
            odd = !odd;
        }
        return ans;
    }
}
// -- 1        (1)
// 3 2 ----    (3)
// 2 --- 7 6   (2)
// --- 7 6 5 4 (4 5 6 7)