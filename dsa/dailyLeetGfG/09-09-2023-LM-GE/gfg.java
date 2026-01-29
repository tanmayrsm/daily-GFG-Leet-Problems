
class Solution
{
    // return the Kth largest element in the given BST rooted at 'root'
    private static int ans;
    private static int ct;
    public int kthLargest(Node root,int K)
    {
        //Your code here
        ans = -1;
        ct = 0;
        RVL(root, K);
        return ans;
    }
    private static void RVL(Node root, int K) {
        if(root == null)    return;
        // if(ct == K) ans = root.data;
        RVL(root.right, K);
        // root.data
        if(ct + 1 == K) ans = root.data;
        ct++;
        RVL(root.left, K);
    }
}