class Solution {
    // Function to find the height of a binary tree.
    int height(Node node) {
        // code here
        // code here
        int ans = 0;
        ArrayDeque<Node> q = new ArrayDeque<>();
        
        q.add(node);
        while(!q.isEmpty()){
            int len = q.size();
            
            while(len -- > 0){
                Node n = q.pop();
                
                //add its left and right child
                if(n.left != null)
                q.offer(n.left);
                
                if(n.right != null)
                q.offer(n.right);
            }
            ans++;
        }
        return ans - 1; 
    }
}