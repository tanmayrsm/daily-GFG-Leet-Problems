// User function Template for Java
class Solution {
    public boolean compute(Node root) {
        int l = 0, r = 0;
        StringBuilder sb = new StringBuilder();
        while(root != null) {
            root = root.next;
            sb.append(root.data + "");
        }
        r = sb.length() - 1;
        while (l < r) {
            if (sb.charAt(r) != sb.charAt(l)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}