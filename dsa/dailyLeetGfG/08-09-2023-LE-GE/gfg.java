
class Solution  // referred soln
{
    // The given root is the root of the Binary Tree
    // Return the root of the generated BST
    Node binaryTreeToBST(Node root)
    {
       // Your code here
       if(root==null) return null;
       ArrayList<Integer>list=new ArrayList<>();
       inorder(root,list);
       Collections.sort(list);
       int[] idx={0};
       inorderChange(root,list,idx);
       return root;
    }
    public void inorder(Node root,ArrayList<Integer>list){
        if(root==null) return;
        inorder(root.left,list);
        list.add(root.data);
        inorder(root.right,list);
    }
    public void inorderChange(Node root,ArrayList<Integer>list,int[] idx){
        if(root==null) return;
        inorderChange(root.left,list,idx);
        root.data = list.get(idx[0]);
        idx[0]++;
        inorderChange(root.right,list,idx);
    }
}