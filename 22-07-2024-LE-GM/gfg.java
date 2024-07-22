class Solution {

    static int largestBst(Node root) {

        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {

            return countNodes(root);

        }

        int left = largestBst(root.left);

        int right = largestBst(root.right);

        return Math.max(left, right);

    }

 

    static boolean isBST(Node node, int min, int max) {

        if (node == null) {

            return true;

        }

        if (node.data < min || node.data > max) {

            return false;

        }

        return isBST(node.left, min, node.data - 1) && isBST(node.right, node.data + 1, max);

    }

 

    static int countNodes(Node node) {

        if (node == null) {

            return 0;

        }

        return 1 + countNodes(node.left) + countNodes(node.right);

    }

}