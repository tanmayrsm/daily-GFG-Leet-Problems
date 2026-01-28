
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
        int n, m, a, b;
        List<Integer> r1, r2, ans;
        r1 = new ArrayList<>();
        r2 = new ArrayList<>();
        ans = new ArrayList<>();
        dfs(root1, r1);
        dfs(root2, r2);
        
        n = r1.size(); m = r2.size();
        a = 0; b = 0;
        
        while (a < n && b < m) {
            if (r1.get(a) <= r2.get(b))
                ans.add(r1.get(a++));
            else ans.add(r2.get(b++));
        }
        while (a < n)   ans.add(r1.get(a++));
        while (b < m)   ans.add(r2.get(b++));
        
        return ans;
    }
    
    private static void dfs (Node root, List<Integer> l) {
        if (root == null)   return;
        dfs(root.left, l);
        l.add(root.data);
        dfs(root.right, l);
    }
}
