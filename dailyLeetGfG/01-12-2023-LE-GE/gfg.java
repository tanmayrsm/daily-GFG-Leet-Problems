//Function should return true if a deadEnd is found in the bst otherwise return false.
class Solution
{
     public static boolean isDeadEnd(Node root){
        //Add your code here.
        ArrayList<Integer> al = new ArrayList<>();
        al.add(0);
        helper(al, root);
        return sol(al, root);
    }
    
    private static void helper(ArrayList<Integer> al, Node root) {
        if(root==null) return;
        helper(al, root.left);
        al.add(root.data);
        helper(al, root.right);
        return;
    }
    
    private static boolean sol(ArrayList<Integer> al, Node root) {
        if(root == null) return false;
        if(root.left == null && root.right == null ) 
            if(al.contains(root.data-1) && al.contains(root.data+1))
                return true;
        return sol(al, root.left) || sol(al, root.right);
    }
}