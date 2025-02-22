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
    int index, n;
    public TreeNode recoverFromPreorder(String traversal) {
         index = 0;
         n = traversal.length();
        return insertNode(traversal,0);
    }
    public TreeNode insertNode(String traversal, int depth){
       if(index >= n)
         return null;
        
        //count the number of dashes
        int dashes = 0;

        while((index+dashes) < n && traversal.charAt(index+dashes) == '-')
           dashes++;
        
        //if dashes not equal to current depth
        if(dashes != depth)
          return null;
        
        index += dashes;

        //to store the value
        int value = 0;

        while(index < n && Character.isDigit(traversal.charAt(index)))
           value = value*10+(traversal.charAt(index++)-'0');

        TreeNode root = new TreeNode(value);

        root.left = insertNode(traversal,depth+1);
        root.right = insertNode(traversal,depth+1);

        return root;
    }
}