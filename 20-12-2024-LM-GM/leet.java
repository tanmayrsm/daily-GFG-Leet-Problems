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
    public TreeNode reverseOddLevels(TreeNode root) {
        DFS(root.left, root.right, 0);
        return root;
    }
    public void DFS(TreeNode leftBacha, TreeNode rightBacha, int lawal){
        if(leftBacha==null || rightBacha==null){
            return;
        }
        if(lawal%2==0){
            int temp=leftBacha.val;
            leftBacha.val=rightBacha.val;
            rightBacha.val=temp;
        }

        DFS(leftBacha.left,rightBacha.right, lawal+1);
        DFS(leftBacha.right,rightBacha.left, lawal+1);
    }
}

