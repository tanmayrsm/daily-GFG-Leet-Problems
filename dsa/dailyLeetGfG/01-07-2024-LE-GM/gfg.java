
/*class Tree{
int data;
    Tree left;
    Tree right;
    Tree(int d){
        data=d;
        left=null;
        right=null;
    }
}

class Node {
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}*/


class GfG 
{
    //Function to make binary tree from linked list.
    public static Tree convert(Node head, Tree node) {
        // add code here
        Queue<Tree> q = new LinkedList<>();
        Tree ans = null;
        while (head != null) {
            Tree t = new Tree(head.data);
            if (!q.isEmpty()) {
                Tree root = q.peek();
                if (root.left != null && root.right != null)
                    q.poll();
                root = q.peek();
                if (root.left == null)
                    root.left = t;
                else if (root.right == null)
                    root.right = t;
                
            } else {
                ans = t;
            }
            q.offer(t);
            head = head.next;
        }
        return ans;
    }
}