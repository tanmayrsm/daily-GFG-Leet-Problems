// User function Template for Java

// class Node
// {
//     int data;
//     Node left, right;

//     public Node(int d)
//     {
//         data = d;
//         left = right = null;
//     }
// }

class Solution {
    // Return the Kth smallest element in the given BST
    ArrayList<Integer>arr=new ArrayList<>();
    public void BST(Node root)
    {
        if(root!=null){
        arr.add(root.data);
        BST(root.left);
        BST(root.right);
        }
        else
        return;
    }
    public int kthSmallest(Node root, int k) {
        // Write your code here
        BST(root);
        Collections.sort(arr);
        if(k>arr.size())
        return -1;
        return arr.get(k-1);
    }
}