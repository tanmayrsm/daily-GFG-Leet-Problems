class Solution {
    public boolean rotateString(String s, String goal) {
        int n = s.length(), m = goal.length();
        return (n == m) && (goal + goal).contains(s);
    }
}