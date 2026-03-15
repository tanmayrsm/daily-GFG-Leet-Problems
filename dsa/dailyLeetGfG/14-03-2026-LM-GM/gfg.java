
/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class NodeModified {
    int data;
    NodeModified left;
    NodeModified right;
    int index;
    NodeModified(int data, int index){
        this.data = data;
        left=null;
        right=null;
        this.index=index;
    }
}

class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.

    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        Queue<NodeModified> q = new LinkedList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        NodeModified n = dfs(root, 0);

        q.offer(n);
        while(!q.isEmpty()) {
            NodeModified x = q.poll();
            if(!tm.containsKey(x.index)) {
                tm.put(x.index, x.data);
            }
            if(x.left != null)
                q.offer(x.left);
            if(x.right != null)
                q.offer(x.right);
        }


        ArrayList<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer, Integer> m : tm.entrySet())
            ans.add(m.getValue());
        return ans;
    }

    private static NodeModified dfs(Node root, int no) {
        if(root == null)    return null;

        NodeModified n = new NodeModified(root.data, no);
        n.left = dfs(root.left,  no - 1);
        n.right = dfs(root.right, no + 1);
        return n;
    }

}