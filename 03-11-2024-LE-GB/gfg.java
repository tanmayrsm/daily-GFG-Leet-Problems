// User function Template for Java
class Solution {
    public boolean isLengthEven(Node head) {
        // code here
        int n = 0;
        while(head != null) {
            head = head.next;
            n++;
        }
        return n % 2 == 0;
    }
}