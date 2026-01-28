
//User function Template for Java
class Solution
{
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node root)
    {
        // Code here
        Queue<ModifiedNode> q = new LinkedList<>();
        q.offer(new ModifiedNode(root, 0));
        ArrayList<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        mp.put(0, root.data);
        
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                ModifiedNode node = q.poll();
                if(node.left != null) {
                    q.offer(new ModifiedNode(node.left, node.level - 1));
                }
                if(node.right != null) {
                    q.offer(new ModifiedNode(node.right, node.level + 1));
                }
                mp.put(node.level, node.data);
            }
        }
        return new ArrayList<>(mp.values());
    }
    public class ModifiedNode {
        Node left;
        Node right;
        int level;
        int data;
        ModifiedNode(Node node, int level) {
            this.left = node.left;
            this.right = node.right;
            this.data = node.data;
            this.level = level;
        }
    }
}