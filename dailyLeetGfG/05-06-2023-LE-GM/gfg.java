
class Solution
{
    //Function to find the least absolute difference between any node
    //value of the BST and the given integer.
    static int minDiff(Node  root, int K) 
    { 
        // Write your code here
        int ans = Integer.MAX_VALUE;
        while(root != null) {
            if(root.data == K)
                return 0;
            else if (K > root.data) {
                ans = Math.min(ans, K - root.data);
                root = root.right;  // as on left side, this diff will increase
            } else {
                ans = Math.min(ans, root.data - K);
                root = root.left;
            }
        }
        return ans;
    } 
}
