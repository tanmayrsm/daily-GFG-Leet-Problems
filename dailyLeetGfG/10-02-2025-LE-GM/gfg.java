class Solution
{
    static long ct;
    static int mod = 1000000007;
    public int sumK(Node root,int k)
    {
        // code here 
        ct = 0;
        dfs(root, k, new ArrayList<>());
        return (int)ct;
    }
    
    // PREORDER traversal
    // top to bottom
    // NOTE :: dont start from leaf, and add in array, as it will add extra runtime, to add all childs
    // from both left and right child, then add parent
    
    private static void dfs(Node root, int k, ArrayList<Integer> arr) {
        if(root == null)    return;
        arr.add(root.data);
        int n = arr.size();
        int sum = 0;
        for(int i = n - 1; i >= 0; i--) {
            sum += arr.get(i);
            if(sum == k) {
                ct++;
                // ct %= mod;
            }
        }
        
        dfs(root.left, k, arr);
        dfs(root.right, k, arr);
        
        arr.remove(n - 1);
    }
}