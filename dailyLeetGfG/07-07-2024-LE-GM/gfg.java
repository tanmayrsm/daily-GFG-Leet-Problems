
/*class Node of the binary tree
class Node
{
    int data;
    Node left, right;
    Node(int key)
    {
        data = key;
        left = right = null;
    }
}*/


class Solution {
    private static ArrayList<Integer> ans;
    public ArrayList<Integer> Ancestors(Node root, int target) {
        // add your code here
        ans = new ArrayList<>();
        dfs(root, target);
        return ans;
    } 
    private static boolean dfs(Node root, int target) {
        if (root == null)   return false;
        Boolean l = dfs(root.left, target);
        Boolean r = dfs(root.right, target);
        if (l || r)
            ans.add(root.data);
        return root.data == target || l || r;
    }
}