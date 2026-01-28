
/*class Node
{
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}*/

import java.util.ArrayList;
import java.util.Deque;

import org.w3c.dom.Node;

class GFG
{
    //Function to store the zig zag order traversal of tree in a list.
	ArrayList<Integer> zigZagTraversal(Node root)
	{
	    //Add your code here.
	    Deque<Node> dq = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        dq.addFirst(root);
        boolean l2r = true;
        while(!dq.isEmpty()) {
            int n = dq.size();
            while(n-- > 0) {
                Node polled = l2r ? dq.pollFirst() : dq.pollLast();
                ans.add(polled.data);
                if(l2r) {
                    if(polled.left != null)
                        dq.offerLast(polled.left);
                    if(polled.right != null)
                        dq.offerLast(polled.right);
                } else {    // offer right then left while tracking r2l wale nodes
                    if(polled.right != null)
                        dq.offerFirst(polled.right);
                    if(polled.left != null)
                        dq.offerFirst(polled.left);
                }
            }
            l2r = !l2r;
        }

        return ans;
	}
}