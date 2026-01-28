class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length(), m = target.length();
        int l = 0, r = 0;
        while (l < n && r < m) {
            while (l < n && start.charAt(l) == '_') {
                l++;
            }
            while (r < m && target.charAt(r) == '_') {
                r++;
            }
            if(r == m || l == n)    break;

            // if characters mismatch, OR 
            // 'R' found in start, but its index in target is lower and vice versa
            if(start.charAt(l) != target.charAt(r) || 
                (start.charAt(l) == 'R' && r < l) || (start.charAt(l) == 'L' && r > l)) {
                return false;
            }
            r++;
            l++;
        }
        while (l < n) {
            if (start.charAt(l) != '_') return false;
            l++;
        }
        while (r < m) {
            if (target.charAt(r) != '_') return false;
            r++;
        }
        return true;
    }
}