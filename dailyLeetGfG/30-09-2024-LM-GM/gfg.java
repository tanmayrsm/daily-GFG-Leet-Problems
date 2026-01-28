import java.util.ArrayList;
import java.util.List;

// User function Template for Java

/*
class Node
{
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}

*/
class Solution {
    // Function to return a list of integers denoting the node
    // values of both the BST in a sorted order.
    public List<Integer> merge(Node root1, Node root2) {
        // Write your code here
        List<Integer> a1 = dfs(root1, new ArrayList<>());
        List<Integer> a2 = dfs(root2, new ArrayList<>());
        return merge(a1, a2);
    }
    private void dfs(Node root, List<Integer> arr) {
        if(root == null)    return;
        dfs(root.left);
        arr.add(root.val);
        dfs(root.right);
    }
    private List<Integer> merge(List<Integer> a1, List<Integer> a2) {
        List<Integer> res = new ArrayList<>();
        int n = a1.size(), m = a2.size(), l = 0, r = 0;
        while (l < n && r < m) {
            if(a1.get(l) <= a2.get(r)) 
                res.add(a1.get(l++));
            else res.add(a2.get(r++));
        }
        while (l < n) 
            res.add(a1.get(l++));
        while (r < m) 
            res.add(a2.get(r++));

        return res;
    }
}