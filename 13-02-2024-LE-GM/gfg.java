//User function Template for Java
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
class Solution{
    private static Set<Node> newNodeList;
    Node cloneGraph(Node node){
        newNodeList = new HashSet<>();
        return makeClone(node);
    }
    private static Node makeClone(Node node) {
        if(newNodeList.contains(node))  return node;
        Node newer = new Node(node.val);
        newNodeList.add(newer);
        for(Node x : node.neighbors) {
            newer.neighbors.add(makeClone(x));
        }
        return newer;
    }
}