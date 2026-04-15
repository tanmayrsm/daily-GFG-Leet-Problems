class Solution {
    String URLify(String s) {
        // code here
        return s.replaceAll("\\ ", "%20");
    }
}