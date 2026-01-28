/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/
/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private int ans;
    public int findMedian(Node root) {
        // Code here
        int n = getTotalNodes(root);
        ans = -1;
        if (n == 1) return root.data;
        int findIndex = 0;
        if (n % 2 == 0) findIndex = n / 2 - 1;
        else findIndex = n / 2;
        getNodeAtIndex(root, 0, findIndex);
        return ans;
    }
    private int getNodeAtIndex(Node root, int currIdx, int findIndex) {
        if (root == null)   return -1;

        int leftFound = getNodeAtIndex(root.left, currIdx, findIndex);
        int c = (leftFound == -1) ? currIdx : leftFound + 1;
        if (c == findIndex && ans == -1) {
            ans = root.data;
        }
        int rightFound = getNodeAtIndex(root.right, c + 1, findIndex);
        return Math.max(c, rightFound);
    }
    private int getTotalNodes(Node root) {
        if (root == null)   return 0;
        int left = getTotalNodes(root.left);
        int right = getTotalNodes(root.right);
        return 1 + left + right;
    }
}