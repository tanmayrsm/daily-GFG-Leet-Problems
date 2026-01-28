
// https://www.youtube.com/watch?v=Xv-T1WQ9wTE
class Solution
{
    private static int moves;
    public static int distributeCandy(Node root)
    {
        //code here
        moves = 0;
        solve(root);
        return moves;
    }
    private static int solve(Node root) {
        if(root == null)    return 0;
        Integer l = solve(root.left);
        Integer r = solve(root.right);
        
        moves += Integer.valueOf(Math.abs(l) + Math.abs(r));
        
        return root.data + l + r - 1;   // pass no of candies in curr node 
        // why -1? cus I need to ensure, that node has atleast one candy
    }
}