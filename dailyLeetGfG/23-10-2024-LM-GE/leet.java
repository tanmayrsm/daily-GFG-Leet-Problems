class Solution {    // reff
    private List<Integer> levelSum;
    public TreeNode replaceValueInTree(TreeNode root) {
        levelSum = new ArrayList<>();
        makeLevelSum(root , 0);
        TreeNode ans = new TreeNode(0);
        makeCousionTree(root , ans , 0);
        return ans;
    }
    private void makeLevelSum(TreeNode root , int level){
        if(root == null) return;
        if(level + 1 > levelSum.size()) levelSum.add(0);
        levelSum.set(level , levelSum.get(level) + root.val);
        makeLevelSum(root.left , level + 1);
        makeLevelSum(root.right , level + 1);
    }

    private void makeCousionTree(TreeNode root , TreeNode ans , int level){
        if(level + 1 > levelSum.size() || root == null) return;
        int siblingSum = 0;
        if(root.left != null) siblingSum += root.left.val;
        if(root.right != null) siblingSum += root.right.val;
        if(root.left != null){
            ans.left = new TreeNode(levelSum.get(level + 1) - siblingSum);
            makeCousionTree(root.left , ans.left , level + 1);
        }
        if(root.right != null){
            ans.right = new TreeNode(levelSum.get(level + 1) - siblingSum);
            makeCousionTree(root.right , ans.right , level + 1);
        }
    }
}