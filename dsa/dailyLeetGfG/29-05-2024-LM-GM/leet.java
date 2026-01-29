class Solution {    // reff
    public int numSteps(String s) {
        int ans = 0;
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();  // Reverse the string to process from LSB to MSB

        while (sb.length() > 1) {  // Continue until the string length becomes 1
            ans++;
            if (sb.charAt(0) == '1') {  // If the current bit is '1'
                int i = 0;
                while (i < sb.length() && sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                    i++;
                }
                if (i == sb.length()) {
                    sb.append('1');  // If all bits were '1', add a new '1' at the end
                } else {
                    sb.setCharAt(i, '1');  // Change the first '0' to '1'
                }
            } else {  // If the current bit is '0'
                sb.deleteCharAt(0);  // Remove the current bit (divide by 2)
            }
        }

        return ans;  // Return the total number of steps
    }
}