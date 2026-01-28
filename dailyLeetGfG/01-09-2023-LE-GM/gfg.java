class Tree
{
    public static void printCorner(Node node)
    {
        
        // add your code here   
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()) {
            int n = q.size();
            int ct = 0;
            while(ct < n) {
                Node k = q.poll();
                if(k.left != null)
                    q.offer(k.left);
                if(k.right != null)
                    q.offer(k.right);

                if(ct == 0 || ct == n - 1)
                    System.out.print(k.data + " ");
                ct++;
            }
        }
    }
    
}