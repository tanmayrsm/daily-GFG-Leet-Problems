
class Solution {    // referred soln
    public static ArrayList < Integer > KDistanceNodes(Node root, int target, int k) {
      // return the sorted list of all nodes at k dist
  
      ArrayList < Node > path = new ArrayList < > ();
      ArrayList < Integer > result = new ArrayList < > ();
  
      Node blockNode = null;
      hasPath(root, target, path);
      int n = path.size();
      for (int i = n - 1; i >= 0; i--) {
        if (i == n - 1) {
          blockNode = null;
        } else {
          blockNode = path.get(i + 1);
        }
        moveDown(path.get(i), k - n + i + 1, result, blockNode);
      }
      Collections.sort(result);
      return result;
    }
  
    public static boolean hasPath(Node root, int x, ArrayList < Node > path) {
      if (root == null)
        return false;
      path.add(root);
  
      if (root.data == x)
        return true;
      if (hasPath(root.left, x, path) || hasPath(root.right, x, path))
        return true;
  
      path.remove(path.size() - 1);
      return false;
    }
  
    public static void moveDown(Node root, int k, ArrayList < Integer > result, Node blockNode) {
      if (root == null || k < 0 || root == blockNode)
        return;
      if (k == 0) {
        result.add(root.data);
        return;
      }
      moveDown(root.left, k - 1, result, blockNode);
      moveDown(root.right, k - 1, result, blockNode);
    }
  }