class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String o : operations) {
            if (o.charAt(0) == '+' || o.charAt(1) == '+')   ans++;
            else ans--;
        }
        return ans;
    }
}