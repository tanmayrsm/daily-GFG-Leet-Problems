class Solution {
    ArrayList<String> ans;
    public ArrayList<String> binstr(int n) {
        // code here
        ans = new ArrayList<>();
        solve(0, new char[n], n);
        return ans;
    }
    private void solve(int ct, char[] curr, int n) {
        if (ct == n) {
            ans.add(new String(curr));
            return;
        }
        curr[ct] = '0';
        solve(ct + 1, curr, n);
        curr[ct] = '1';
        solve(ct + 1, curr, n);

    }
}