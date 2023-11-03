class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int curr = 0;
        for(int i = 1; i <= n && curr < target.length; i++) {
            if(target[curr] == i) {
                curr++;
                ans.add("Push");
            }  else {
                ans.add("Push"); ans.add("Pop");
            }
        }
        return ans;
    }
}