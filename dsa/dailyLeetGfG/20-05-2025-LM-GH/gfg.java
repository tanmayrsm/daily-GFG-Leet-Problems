

class Solution
{
    /*class Node {
    	int data;
    	Node left;
    	Node right;

    	Node(int data) {
    		this.data = data;
    		left = null;
    		right = null;
    	}
    }*/

    static class ModNode {
        int data;
        Node left, right, parent;
        ModNode(int data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
        ModNode(int data, Node left, Node right, Node parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private static Set<Node> vis;
    private static Map<Node, ModNode> mapper;

    private static ModNode starter;
    public static int minTime(Node root, int target) {
        // Your code goes here
        Queue<ModNode> q = new LinkedList<>();
        int ans = -1;
        vis = new HashSet<>();
        mapper = new HashMap<>();
        dfs(root, null, target);

        q.offer(starter);

        while(!q.isEmpty()) {
            int n = q.size();
            // System.out.println("+++++++++++");
            while (n-- > 0) {
                ModNode newer = q.poll();
                // System.out.println(newer.data + "::");
                if (newer.left != null && !vis.contains(newer.left)) {
                    q.offer(mapper.get(newer.left));
                    vis.add(newer.left);
                }
                if (newer.right != null && !vis.contains(newer.right)) {
                    q.offer(mapper.get(newer.right));
                    vis.add(newer.right);
                }
                if (newer.parent != null && !vis.contains(newer.parent)) {
                    q.offer(mapper.get(newer.parent));
                    vis.add(newer.parent);
                }
            }
            ans++;
        }
        return ans;

    }

    private static void dfs(Node root, Node parent, int target) {
        if (root == null)   return;
        ModNode modNode = new ModNode(root.data, root.left, root.right, parent);
        mapper.put(root, modNode);

        if (root.data == target) {
            starter = modNode;
            vis.add(root);
        }
        dfs(root.left, root, target);
        dfs(root.right, root, target);
    }
}