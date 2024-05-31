
// User function Template for Java
class Solution {
    static int swapNibbles(int n) { // reff
        // code here
        return (n>>4)|((n&15)<<4);
    }
}