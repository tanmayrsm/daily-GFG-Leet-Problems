class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for(int i = 0; i < l.length; i++) {
            List<Integer> curr = new ArrayList<>();
            for(int j = l[i]; j <= r[i]; j++)
                curr.add(nums[j]);
            Collections.sort(curr);
            if(curr.size() <= 2)    ans.add(true);
            else {
                int cd = curr.get(1) - curr.get(0), k = 2;
                for(k = 2; k < curr.size(); k++) {
                    if(curr.get(k) - curr.get(k - 1) != cd) {
                        ans.add(false);
                        break;
                    }
                }
                if(k == curr.size())    ans.add(true);
            }
        }
        return ans;
    }
}