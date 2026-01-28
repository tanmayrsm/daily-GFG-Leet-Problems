
class GfG
{
    //Function to find the data of nth node from the end of a linked list.
    int getNthFromLast(Node head, int n)
    {
    	Queue<Node> q = new LinkedList<>();
		q.offer(root);
		int ans = 0;
		while(!q.isEmpty()) {
			int n = q.size();
			ans++;
			while(n-- >= 0) {
				Node x = q.poll();
				if(x.left != null)  q.offer(x.left);
				if(x.right != null) q.offer(x.right);
			}
		}
		return ans;
    }
}
