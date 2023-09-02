
class Solution{
    public int getCount(Node node, int bud)
    {
        //code here  
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        int level = 0;
        int ans = 0;
        while(!q.isEmpty()) {
            int n = q.size();
            level++;
            while(n-- > 0) {
                Node x = q.poll();
                if(x.left == null && x.right == null)  {
                    if(bud >= level)
                         ans++;
                     bud -= level;
                     if(bud < 0)    return ans;
                }
                
                if(x.left != null)
                    q.offer(x.left);
                if(x.right != null)
                    q.offer(x.right);
            }
        }
        return ans;
    }
}