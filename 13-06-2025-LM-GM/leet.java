class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int len = nums.length, i = 0, j = nums[len - 1] - nums[0];
        int answer = 0;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            int pairs = checkPairs(nums, mid, len);
            if (pairs >= p) {
                answer = mid;
                j = mid - 1;
            }
            else i = mid + 1;
        }
        return answer;
    }
    public int checkPairs(int[] nums, int mid, int len) {
        int pairs = 0;
        int i = 0, j = 1;
        while (j < len) {
            if (nums[j] - nums[i] <= mid) {
                pairs++;
                j++;
                i++;
            }
            j++;
            i++;
        }
        return pairs;
    }
}