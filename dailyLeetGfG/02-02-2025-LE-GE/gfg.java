class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        ArrayList<ArrayList<Integer>> fans=new ArrayList<>();
        if(root==null) return fans;
        
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty())
        {
            int s=q.size();
            ArrayList<Integer> tmp=new ArrayList<>();
            for(int i=0;i<s;i++)
            {
                Node t=q.poll();
                tmp.add(t.data);
                if(t.left!=null)
                    q.add(t.left);
                if(t.right!=null)
                    q.add(t.right);
            }
            fans.add(tmp);
        }
        return fans;
    }
}