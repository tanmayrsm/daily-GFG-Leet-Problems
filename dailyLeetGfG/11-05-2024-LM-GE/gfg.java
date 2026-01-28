
// User function Template for Java

class Solution {
    static List<Integer> jugglerSequence(int n) {
        // code here
        List<Integer> ans = new ArrayList<>();
        ans.add(n);
        for(int i = 1; ans.get(i - 1) != 1; i++) {
            int last = ans.get(i - 1);
            if(last % 2 == 0) {
                ans.add((int) Math.sqrt(last));
            } else {
                ans.add((int)Math.pow( Math.sqrt(last), 3));
            }
        }
        return ans;
    }
}