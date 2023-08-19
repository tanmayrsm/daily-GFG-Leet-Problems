class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i = 0; i < k; i++)
            if(tm.containsKey(nums[i]))
                tm.put(nums[i], tm.get(nums[i]) + 1);
            else    tm.put(nums[i], 1);
        ans.add(tm.lastKey());

        for(int i = k; i < n; i++) {
            int popIndexElem = nums[i - k];
            if(tm.get(popIndexElem) == 1)
                tm.remove(popIndexElem);
            else    tm.put(popIndexElem, tm.get(popIndexElem) - 1);

            if(tm.containsKey(nums[i]))
                tm.put(nums[i], tm.get(nums[i]) + 1);
            else tm.put(nums[i], 1);

            ans.add(tm.lastKey());
        }
        // return ans.stream().mapToInt(e -> e).toArray();  // TAKES TOO MUCH TIME, hence adding manually

        int[] ret = new int[ans.size()];
        int ct = 0;
        for(int i = 0; i < ans.size(); i++)
            ret[ct++] = ans.get(i);
        return ret;
    }
}