/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public boolean isSubPath(ListNode head, TreeNode root) {
        TreeNode curr = root;

        Stack<TreeNode> st = new Stack<>();
        while (!st.isEmpty() || curr != null) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            TreeNode poped = st.pop();
            if(poped.val == head.val) {
                if(trackNode(poped, head.next))  
                    return true;
            }
            if (poped.right != null) {
                curr = poped.right;
            }
        }
        return false;
    }

    private static boolean trackNode(TreeNode root, ListNode head) {
        if (head == null)   return true;
        if (root == null)   return false;
        
        boolean goLeft = false, goRight = false;
        if (root.left != null && root.left.val == head.val) 
            goLeft = trackNode(root.left, head.next);
        
        if (root.right != null && root.right.val == head.val) 
            goRight = trackNode(root.right, head.next);

        return goLeft || goRight;
    }
}