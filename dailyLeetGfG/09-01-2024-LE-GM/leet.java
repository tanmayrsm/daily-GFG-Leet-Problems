/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // traverse al leaf nodes of root1
        Stack<TreeNode> t1 = new Stack<>();
        List<Integer> arr1 = new ArrayList<>();

        // var for traversing root2
        Stack<TreeNode> t2 = new Stack<>();
        List<Integer> arr2 = new ArrayList<>();

        // NORMAL DFS
        while(root1 != null || t1.size() > 0) {
            while(root1 != null) {
                t1.push(root1);
                root1 = root1.left;
            }
            root1 = t1.pop();
            if(root1.right == null && root1.left == null) {
                // leaf node
                arr1.add(root1.val);
            }
            root1 = root1.right;
        }

        while(root2 != null || t2.size() > 0) {
            while(root2 != null) {
                t2.push(root2);
                root2 = root2.left;
            }
            root2 = t2.pop();
            if(root2.right == null && root2.left == null) {
                // leaf node
                arr2.add(root2.val);
            }
            root2 = root2.right;
        }
        if(arr1.size() != arr2.size())
            return false;
        for(int i = 0; i < arr1.size(); i++) {
            if(arr1.get(i) != arr2.get(i))
                return false;
        }
        return true;
    }
}