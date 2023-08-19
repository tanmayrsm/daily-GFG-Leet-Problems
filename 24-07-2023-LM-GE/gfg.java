
class Solution{
    //Function to return list containing elements of right view of binary tree.
    ArrayList<Integer> rightView(Node node) {
        //add code here.
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        q.add(node);
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                Node r = q.poll();
                if(n == 0)
                    ans.add(r.data);
                if(r.left != null)
                    q.offer(r.left);
                if(r.right != null)
                    q.offer(r.right);
                
            }
        }
        return ans;
    }
}