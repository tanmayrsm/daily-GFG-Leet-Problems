/*
class Node {
    int data;
    Node left, right;

    public Node(int data){
        this.data = data;
    }
} */
class Solution {
    public int sumOfLongRootToLeafPath(Node root) {
        // code here
        int[] ans = solve(root);
        return ans[1];
    }
    // params[] = [len, sum]
    private int[] solve(Node root) {
        if (root == null)
                return new int[] {0, 0};
        if (root.left == null && root.right == null)
            return new int[] {1, root.data};
        int[] left = solve(root.left);
        int[] right = solve(root.right);
        if (left[0] == right[0]) {
            return new int[] {1 + left[0], root.data + (left[1] > right[1] ? left[1] : right[1])};
        }
        if (left[0] > right[0])
            return new int[] {1 + left[0], root.data + left[1]};
        return new int[] {1 + right[0], root.data + right[1]};
    }
}