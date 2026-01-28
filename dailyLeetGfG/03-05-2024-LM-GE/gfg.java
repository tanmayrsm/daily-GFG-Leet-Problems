
/*
class Node
{
    int data;
    Node left, right;
   Node(int item)    {
        data = item;
        left = right = null;
    }
} */

class Tree
{
     // Recursive function to print right view of a binary tree.
     ArrayList<Integer> Kdistance(Node root, int k) {
          // Your code here
          Queue<Node> q = new LinkedList<>();
          int currDist = 0;
          ArrayList<Integer> ans = new ArrayList<>();
          q.offer(root);

          while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                Node res = q.poll();
                if(currDist == k)   ans.add(res.data);
                if(res.left != null)    q.offer(res.left);
                if(res.right != null)    q.offer(res.right);
            }
            if(!ans.isEmpty())  return ans;
            currDist++;
          }
          
          return ans;
     }
}