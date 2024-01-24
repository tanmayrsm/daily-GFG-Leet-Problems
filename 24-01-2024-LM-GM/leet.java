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
    private static List<Integer> ct;
    private static int ans;
    public int pseudoPalindromicPaths (TreeNode root) {
        ct = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            ct.add(0);
        ans = 0;
        ct.set(root.val - 1, ct.get(root.val - 1) + 1);
        dfs(root);
        return ans;
    }
    private static void dfs(TreeNode root) {
        if(root == null)    return;
        if(root.left == null && root.right == null){
            int oddNos = 0;
            ct.set(root.val - 1, ct.get(root.val - 1) + 1);
            for(int x : ct) {
                System.out.print(x + ":: ");
                
                if(x % 2 == 1)  oddNos++;
            }
                System.out.println();
            ct.set(root.val - 1, ct.get(root.val - 1) - 1);
            
            if(oddNos <= 1) ans++;
            return;
        }
        if(root.left != null) {
            int data = root.left.val;
            ct.set(data - 1, ct.get(data-1) + 1);
        
            dfs(root.left);
            ct.set(data - 1, ct.get(data-1) - 1);
        
        }
        if(root.right != null) {
            int data = root.right.val;
            ct.set(data - 1, ct.get(data-1) + 1);
            dfs(root.right);
            ct.set(data - 1, ct.get(data-1) - 1);
        
        }
        
    }
}