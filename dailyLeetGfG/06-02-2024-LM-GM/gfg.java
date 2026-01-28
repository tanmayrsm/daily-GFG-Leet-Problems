class Solution
{
    //Function to return count of nodes at a given distance from leaf nodes.
    private static int ans;
    private static Set<Node> nodeList;
    private static List<Node> path;
    
    int printKDistantfromLeaf(Node root, int k) {
        // Write your code here
        ans = 0;
        nodeList = new HashSet<>();
        path = new ArrayList<>();
        dfs(root, k, 0);
        return nodeList.size();


    }
    private static void dfs(Node node, int k, int currPathSize) {
        if(node == null)    return ;
        path.add(node);
        if(node.left == null && node.right == null) {   // leaf
            // goto path.size - 1 - k th index, to find node
            if(currPathSize - k >= 0) {
                nodeList.add(path.get(currPathSize - k));
            }
        }
        dfs(node.left, k, currPathSize + 1);
        dfs(node.right, k, currPathSize + 1);
        path.remove(currPathSize);
    }

}