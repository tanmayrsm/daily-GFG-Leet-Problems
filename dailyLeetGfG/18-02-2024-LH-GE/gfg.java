
/*node class of the binary ssearch tree
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
class Solution
{
    public static int sumOfLeafNodes(Node root)
    {
        // code here 
        return solve(root);
        
    }
    private static int solve(Node node) {
        if(node == null)    return 0;
        if(node.left == null && node.right == null) return node.data;
        return solve(node.left) + solve(node.right);
    }
}