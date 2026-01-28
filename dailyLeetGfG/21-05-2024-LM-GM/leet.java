import java.util.ArrayList;
import java.util.List;

class Solution {
    private static List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        solve(nums, 0, new ArrayList<>(), 0);
        return ans;
    }
    private static void solve(int[] nums, int curr, List<Integer> cList, int cSize) {
        if(curr == nums.length) {
            ans.add(new ArrayList<>(cList));
            return;
        }
        cList.add(nums[curr]);
        solve(nums, curr + 1, cList, cSize + 1);
        cList.remove(cSize);

        solve(nums, curr + 1, cList, cSize);
    }
}