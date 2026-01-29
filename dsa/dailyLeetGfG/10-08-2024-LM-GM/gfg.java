
class Solution {
    // Function to rotate a linked list.
    public Node rotate(Node head, int k) {
        // add code here
        Node fHead = head, fgo = head, ans = head, ansHead = head;
        int ct = 1;
        while (ct < k) {
            fgo = fgo.next;
            ct++;
        }
        if (fgo == null || fgo.next == null)    return head;
        ans = fgo.next;
        ansHead = ans;
        fgo.next = null;
        while (ans != null && ans.next != null) {
            ans = ans.next;
        }
        if (ans != null)
            ans.next = fHead;

        return ansHead;
    }
}