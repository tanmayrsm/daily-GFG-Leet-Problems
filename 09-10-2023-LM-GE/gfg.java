
class Solution {
    //Function to find the height of a binary tree.
    int height(Node node) 
    {
        // code here 
        Queue<Node> q = new LinkedList<>();
        int ans = 0, prevSize = 1, newSize = 0;
        q.offer(node);
        while(prevSize != 0) {  // refrain using q.isEmpty(), adds TLE
            ans++;
            int n = prevSize;   // refrain using q.size(), adds TLE
            while(n-- > 0) {
                Node x = q.poll();
                if(x.left != null) {
                    q.offer(x.left);
                    newSize++;
                }
                if(x.right != null) {
                    q.offer(x.right);
                    newSize++;
                }
            }
            prevSize = newSize;
            newSize = 0;
        }
        return ans;
        
    }
}
