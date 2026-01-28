    
/*node class of the binary tree
class Node
{
    int data;
    Node left, right;
    Node(int key)
    {
        data = key;
        left = right = null;
    }
}*/
class Solution {
    // Function to construct binary tree from parent array.
    public Node createTree(int parent[]) {
        // Your code here
        Map<Integer, Node> mp = new HashMap<>();
        Node root = null;
        for (int i = 0; i < parent.length; i++) {
            int PARENT = parent[i], child = i;
            Node p = null, c = null;
            if (!mp.containsKey(PARENT) && PARENT != -1)
                mp.put(PARENT, new Node(PARENT));
            if (!mp.containsKey(child))
                mp.put(child, new Node (child));
            p = mp.get(PARENT);
            c = mp.get(child);
            if (PARENT == -1) {
                root = c;
            } else {
                if (p.left == null)
                    p.left = c;
                else p.right = c;
            }
        }
        return root;
    }
}