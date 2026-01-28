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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> store = new HashSet<>();
        for(int x : to_delete)
            store.add(x);

        // dfs logic
        Stack<TreeNode> st = new Stack<>();
        if(!store.contains(root.val))
            ans.add(root);
        st.push(root);
        while(!st.isEmpty()) {
            TreeNode node = st.pop();
            boolean addChild = store.contains(node.val);
            if(node.right != null) {
                st.push(node.right);
                if(addChild && !store.contains(node.right.val)) {
                    ans.add(node.right);    
                    node.right = null;
                }
                else if (store.contains(node.right.val)) {
                    node.right = null;
                }
            }
            if(node.left != null) {
                st.push(node.left);
                if(addChild && !store.contains(node.left.val)) {
                    ans.add(node.left);
                    node.left = null;
                } 
                else if (store.contains(node.left.val)) {
                    node.left = null;
                }
            }
        }

        return ans;
    }
}