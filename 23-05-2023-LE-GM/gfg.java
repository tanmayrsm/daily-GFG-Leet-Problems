
class Solution
{
    static int s;
    static int e;
    public Node constructBTree(int pre[], int preM[], int size)
    {
        // your code here
        Node ans = null;
        s = 1;
        e = size - 1;
        ans = new Node(pre[0]);
        construct(ans, pre, preM);
        return ans;
    }
    private static void construct(Node n, int[] pre, int[] preM) {
        if(n.data != preM[e]) {
            n.left = new Node(pre[s++]);
            construct(n.left, pre, preM);
            n.right = new Node(pre[s++]);
            construct(n.right, pre, preM);
        }
        e--;
    }
    
}