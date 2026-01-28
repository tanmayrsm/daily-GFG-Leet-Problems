
//User function Template for Java

/* A Binary Tree node
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/

import java.lang.classfile.components.ClassPrinter.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Tree
{
    //Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root) {
        // Your code here
        // BFS
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            int k = 0;
            while (k < n) {
                Node elem = q.poll();
                if (k == 0)
                    ans.add(elem.data);
                if (elem.left != null)
                    q.offer(elem.left);
                if (elem.right != null)
                    q.offer(elem.right);
                k++;
            }
        }
        return ans;
    }
    
}