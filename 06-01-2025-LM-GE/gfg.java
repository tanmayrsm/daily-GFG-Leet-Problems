class Solution {
    public List<Integer> sumClosest(int[] arr, int target) {
        // code here
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;
        if (l == r) return ans;
        ans.add(arr[l]); ans.add(arr[r]);
        while(l < r) {
            int currSum = arr[l] + arr[r];
            int currSSum = ans.get(0) + ans.get(1);
            if (Math.abs(target - currSum) < Math.abs(target - currSSum)) {
                ans.set(0, arr[l]);
                ans.set(1, arr[r]);
            } else if (Math.abs(target - currSum) == Math.abs(target - currSSum)) {
                if (ans.get(1) - ans.get(0) < arr[r] - arr[l]) {
                    ans.set(0, arr[l]);
                    ans.set(1, arr[r]);
                }
            }
            if (currSum > target)   r--;
            else if (currSum < target) l++;
            else break;
        }
        return ans;
    }
}