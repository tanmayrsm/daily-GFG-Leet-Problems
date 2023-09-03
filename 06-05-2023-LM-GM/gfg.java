
class Solution
{
    private static int ans;
    public static int goodSubtrees(Node root,int k) {
        ans = 0;
        dfs(root, k);
        return ans;
    }
    private static Set<Integer> dfs(Node root, int k) {
        if(root == null) {
            return new HashSet<>();
        }

        Set<Integer> leftSub = dfs(root.left, k);
        Set<Integer> rightSub = dfs(root.right, k);
        Set<Integer> res = new HashSet<>();
        if(leftSub == null || rightSub == null) // exhausted count for distinct elements over K
            return null;
        res.addAll(leftSub);
        res.addAll(rightSub);
        res.add(root.data);
        if(res.size() <= k)
            ans++;
        return res.size() > k ? null : res;
    }
}