
class Solution
{
    //Function to find the nodes that are common in both BST.
    static Set<Integer> t1;
    static Set<Integer> ans;
	public static ArrayList<Integer> findCommon(Node root1,Node root2)
    {
        //code here
        ans = new HashSet<>();
       t1 = new HashSet<>();
       dfs(root1, false);
       dfs(root2, true);
       ArrayList<Integer> j = new ArrayList<>(ans);
       Collections.sort(j);
       return j;
    }
    
    // inorder traversal ensures, that elements added in ans are in asc order
    private static void dfs(Node root, boolean b) {
        if(root == null)    return;
        dfs(root.left, b);
        if(b && t1.contains(root.data)) {
            ans.add(root.data);
        }
        else    t1.add(root.data);
        dfs(root.right, b);
    }
    
}
