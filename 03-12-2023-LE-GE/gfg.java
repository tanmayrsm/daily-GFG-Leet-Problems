
class Solution
{
    private static int ans;
    private static int X;
	public static int countPairs(Node root1, Node root2, int x)
	{
		// Code here
		ans = 0;
		X = x;
		dfs(root1, root2);
		return ans;
	}
	private static void dfs(Node r1, Node r2) {
	    if(r1 == null)  return;
	    dfs(r1.left, r2);
	    ct(r1.data, r2);
	    dfs(r1.right, r2);
	}
	private static void ct(int n1, Node r2) {
	    if(r2 == null)  return;
	    if(r2.data + n1 == X)
	        ans++;
        if(r2.data + n1 <= X) {
            ct(n1, r2.right);
        }   else ct(n1, r2.left);
	}
}