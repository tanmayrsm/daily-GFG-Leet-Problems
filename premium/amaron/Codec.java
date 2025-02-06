
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null)   return sb.toString();

        sb.append(String.valueOf(root.val) + "<->");
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            StringBuilder curr = new StringBuilder();
            while (n-- > 0) {
                TreeNode poll = q.poll();
                if (poll.left != null)   {
                    q.offer(poll.left);
                    curr.append(String.valueOf(poll.left.val) + "-");
                } else curr.append("N-");

                if (poll.right != null) {
                    q.offer(poll.right);
                    curr.append(String.valueOf(poll.right.val));
                } else curr.append("N");

                if (n > 0)  
                    curr.append("-");
            }
            sb.append(curr);
            if (!q.isEmpty())
                sb.append("<->");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty())
            return null;
        String[] levels = data.split("\\<->");
        for(String level : levels)
            System.out.println("data ::" + level);
        
        int j = 1;
        
        TreeNode ansHead = null;
        ansHead = new TreeNode(Integer.parseInt(levels[0]));
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(ansHead);

        while (!q.isEmpty() && j < levels.length) {
            TreeNode p = q.poll();
            String[] nodesArLevel = levels[j++].split("-");
            for (int i = 0; i < nodesArLevel.length; i += 2) {
                if (nodesArLevel[i].equals("N")) {
                    p.left = null;
                } else {
                    p.left = new TreeNode(Integer.parseInt(nodesArLevel[i]));
                    q.offer(p.left);
                }
                if (nodesArLevel[i + 1].equals("N")) {
                    p.right = null;
                } else {
                    p.right = new TreeNode(Integer.parseInt(nodesArLevel[i + 1]));
                    q.offer(p.right);
                }
                p = q.poll();
            }
        }

        return ansHead;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));