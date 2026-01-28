class Solution {    // reff
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> decQ = new LinkedList<>();
        Deque<Integer> incQ = new LinkedList<>();
        int ans = 0;
        int left = 0;

        for (int right = 0; right < nums.length; ++right) {
            int num = nums[right];

            while (!decQ.isEmpty() && num > decQ.peekLast()) {
                decQ.pollLast();
            }
            decQ.addLast(num);

            while (!incQ.isEmpty() && num < incQ.peekLast()) {
                incQ.pollLast();
            }
            incQ.addLast(num);

            while (decQ.peekFirst() - incQ.peekFirst() > limit) {
                if (decQ.peekFirst() == nums[left]) {
                    decQ.pollFirst();
                }
                if (incQ.peekFirst() == nums[left]) {
                    incQ.pollFirst();
                }
                ++left;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
// nums = [8,2,4,7], limit = 4
// [8, 10, 14, 21]
// [------ 8]