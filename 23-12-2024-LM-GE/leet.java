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
    public int minimumOperations(TreeNode root) {
        // apply bfs to collect the nodes at the same level
        // then the problem boils down to --> min no of swaps req to sort the arr --> use greedy approach
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        int minOperations = 0;
        while(!q.isEmpty()){
            int size = q.size();
            //iterating over the {level} elements of the binary tree
            int levelNodes[] = new int[size];
            int sortedNodes[] = new int[size];
            Map<Integer,Integer> elementIndexMap = new HashMap<>();
            int index =0;
            while(size-->0){
                TreeNode node = q.poll();
                if(null!=node.left){
                    q.add(node.left);
                } 
                if(null!=node.right){
                    q.add(node.right);
                }
                levelNodes[index]=node.val;
                sortedNodes[index]=node.val;
                // as all the elements are unique , the index for a value wont get overwritten in the map
                elementIndexMap.put(node.val,index);
                index++;
            }


            // now use greedy approach to solve min swaps req to sort the array
            Arrays.sort(sortedNodes);
            for(int i=0;i<levelNodes.length;i++){
                if(levelNodes[i]!=sortedNodes[i]){
                    minOperations++;
                    int j=elementIndexMap.get(sortedNodes[i]);
                    //if u update the index of levelNodes[j] , it doesnt help much as levelNodes[j] comes and sits in ith index and already in the next iteration we will move to i+1th index
                    // so only update the index of the element that matters
                    // u can swap the values actually i,j in levelNodes arr , but whats the use, u r scanning from left to right , so values 0...i are not going to help much
                    // swap(levelNodes,i,j);
                    elementIndexMap.put(levelNodes[i],j);
                    levelNodes[j]=levelNodes[i];
                }
            } 
        }

        return minOperations;
    }
}
