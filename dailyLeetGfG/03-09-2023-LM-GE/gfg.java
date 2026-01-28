
class Solution  
{ 
    private boolean areIsomorphicUtil(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;

        if (root1.data != root2.data)
            return false;

        boolean case1 = areIsomorphicUtil(root1.left, root2.left) &&
                        areIsomorphicUtil(root1.right, root2.right);

        boolean case2 = areIsomorphicUtil(root1.left, root2.right) &&
                        areIsomorphicUtil(root1.right, root2.left);

        return case1 || case2;
    }
    boolean isIsomorphic(Node root1, Node root2) {
        if (areIsomorphicUtil(root1, root2))
            return true;
        return false;
    }
    
}    