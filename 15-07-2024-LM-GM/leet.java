import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, Integer> mp = new HashMap<>();   // parent : child
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (int[] description : descriptions) {
            TreeNode parent = nodeMap.containsKey(description[0]) ? nodeMap.get(description[0]) : new TreeNode(description[0]), child = nodeMap.containsKey(description[1]) ? nodeMap.get(description[1]) : new TreeNode(description[1]);

            if (!mp.containsKey(child)) {
                mp.put(description[1], description[0]);
            }
            nodeMap.put(description[0], parent);
            nodeMap.put(description[1], child);

            if (description[2] == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        for(Map.Entry<Integer, Integer> e : mp.entrySet()) {
            // System.out.println(e.getKey() + "::" + e.getValue());
            if (!mp.containsKey(e.getValue()))  
                return nodeMap.get(e.getValue());
        }
        return null;
    }
}
// Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
// Output: [1,2,null,null,3,4]
// Explanation: The root node is the node with value 1 since it has no parent.
// The resulting binary tree is shown in the diagram.

// [parent, child, isleft]
// child1 : parent
// child2 : parent
//