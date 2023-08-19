class Solution {
    private static List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        ans = new ArrayList<>();
        solve(n, nums, 0, n - 1);
        return ans;
    }

    private static void solve(int n, int[] rem, int l, int r) {
        if (l == r)
            ans.add(Arrays.stream(rem).boxed().collect(Collectors.toList()));

        else {
            for (int i = l; i <= r; i++) {
                swapp(rem, l, i);
                solve(n, rem, l + 1, r);
                swapp(rem, l, i);
            }
        }
    }

    private static void swapp(int[] rem, int i, int j) {
        int temp = rem[i];
        rem[i] = rem[j];
        rem[j] = temp;
    }
}