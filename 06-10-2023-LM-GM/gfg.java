class Solution
{
    public static void rearrange(Node root) {
        Node alternate = null, start = root, startRoot = root;
        int ct = 1;
        Node curr;
        while(root != null) {
            curr = root;
            root = root.next;
            if(ct % 2 == 0) {
                start.next = null;
                if(alternate == null) {
                    alternate = curr;
                    alternate.next = null;
                }
                else    {   // storing in a reverse fasion
                    curr.next = alternate;
                    alternate = curr;
                }
            } else {
                if(ct > 1) {
                    start.next = curr;
                    start = start.next;
                }
            }
            if(root == null) {
                start.next = alternate;
            }
            ct++;
        }

    }
}
