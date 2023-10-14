
class Solution {
    static int ans;
    public static int floor(Node root, int x) {
        // Code here
        ans = -1;
        dfs(root, x);
        return ans;
    }
    private static void dfs(Node root, int target) {
        if(root == null) return;
        if(root.data > target) {
            dfs(root.left, target);
            return;
        } 
        ans = ans == -1 ? 
              root.data : 
              (Math.abs(target - root.data) < Math.abs(ans - target) ? 
                root.data : 
                ans);
        dfs(root.right, target);
    }
}