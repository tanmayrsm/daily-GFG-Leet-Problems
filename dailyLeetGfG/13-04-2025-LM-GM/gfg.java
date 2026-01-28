/*
    class Node{
        int val;
        ArrayList<Node> neighbors;
        public Node(){
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val){
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors){
            this.val = val;
            this.neighbors = neighbors;
        }
    }
*/
class Solution {
    Set<Node> vis;
    Node cloneGraph(Node node) {
        // code here
        vis = new HashSet<>();
        Node ans = new Node(node.val);
        dfs(node, ans);
        return ans;
    }
    private void dfs(Node node, Node nwr) {
        if (vis.contains(node)) return;
        vis.add(node);
        for(Node neigh : node.neighbors) {
            if (!vis.contains(neigh)) {
                Node newer = new Node(neigh.val);
                nwr.neighbors.add(newer);
                dfs(neigh, newer);
            }
        }
    }
}