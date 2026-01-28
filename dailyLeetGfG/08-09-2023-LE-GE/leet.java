class Solution {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> ans = new ArrayList<>();

        ans.add(new ArrayList<>(Arrays.asList(1)));
        if(n == 1)  return ans;
        ans.add(new ArrayList<>(Arrays.asList(1, 1)));
        if(n == 2)  return ans;

        int lastptr = 1;
        for(int ct = 1; ct <= n - 2; ct++) {
            List<Integer> x = new ArrayList<>();
            x.add(1);   // first 1
            List<Integer> last = ans.get(lastptr);
            int l = 0, r = 1;
            for(int k = 1; k <= ct; k++) {
                x.add(last.get(l) + last.get(r));
                l++;
                r++;
            }
            x.add(1);   // last 1
            ans.add(x);
            lastptr++;
        }
        return ans;
    }
}