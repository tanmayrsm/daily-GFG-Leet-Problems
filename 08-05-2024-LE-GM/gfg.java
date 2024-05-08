
/*

Definition for Binary Tree Node
class Node
{
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private static ArrayList<ArrayList<Integer>> ans;
    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
        // code here
        ans = new ArrayList<>();
        solve(root, new ArrayList<>(), 0);
        return ans;
    }
    private static void solve(Node root, ArrayList<Integer> curr, int size) {
        if(root == null)    return;
        curr.add(root.data);
        if(root.left == null && root.right == null) {
            ans.add(new ArrayList<>(curr));
            curr.remove(size);
            return;
        }
        solve(root.left, curr, size + 1);
        solve(root.right, curr, size + 1);
        curr.remove(size);
    }
}
