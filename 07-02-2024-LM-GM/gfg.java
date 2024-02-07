// FUNCTION CODE
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
   Node(int item)    {
        data = item;
        left = right = null;
    }
} */


/* Should return minimum distance between a and b
   in a tree with given root*/
class GfG {
    private static Map<Integer, Integer> dist;
    private static int ans;
    int findDist(Node root, int a, int b) {
        // Your code here
        if(a == b)  return 0;
        dist = new HashMap<>();
        dist.put(a, -1); dist.put(b, -1);
        ans = -1;
        dfs(root, a, b);
        return ans;
    }
    private static Set<Integer> dfs(Node root, int a, int b) {
        if(root == null || ans != -1)    return new HashSet<>();
        Set<Integer> leftSide = dfs(root.left, a, b);
        Set<Integer> rightSide = dfs(root.right, a, b);
        Set<Integer> ret = new HashSet<>();
        ret.addAll(rightSide);
        ret.addAll(leftSide);
        
        if(root.data == a) {
            dist.put(a, 0);
            ret.add(a);
            if(ans == -1 && (ret.contains(b)))
                ans = dist.get(b);
        } else if (root.data == b) {
            dist.put(b, 0);
            ret.add(b);
            if(ans == -1 && (ret.contains(a)))
                ans = dist.get(a);
        } 
        if(ans == -1 && (leftSide.contains(a) && rightSide.contains(b)) || (leftSide.contains(b) && rightSide.contains(a))) {
            ans = dist.get(a) + dist.get(b);
        }
        if(ans == -1) {
            if(dist.get(a) > -1 && (root.data == a || ret.contains(a)))    dist.put(a, dist.get(a) + 1);
            if(dist.get(b) > -1 && (root.data == b || ret.contains(b)))    dist.put(b, dist.get(b) + 1);
        }
        
        return ret;
    }
}