class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Map<Integer, Integer> mp = new HashMap<>();
        List<TreeSet<Integer>> maxCt = new ArrayList<TreeSet<Integer>>();   // max count wont exceed 'n'
        for (int i = 0; i <= n; i++) {
            TreeSet<Integer> ts = new TreeSet<>((Integer a, Integer b) -> Integer.compare(b, a));
            maxCt.add(ts);
        }

        for (int i = 0; i < k; i++) {
            if (mp.containsKey(nums[i])) {
                int ct = mp.get(nums[i]);
                maxCt.get(ct).remove(nums[i]);
                maxCt.get(ct + 1).add(nums[i]);
                mp.put(nums[i], mp.get(nums[i]) + 1);
            } else {
                mp.put(nums[i], 1);
                maxCt.get(1).add(nums[i]);
            }
        }
        for (int i = 0; i < n - k + 1; i++) {
            int currSum = 0, currX = 0, last = n;
            while(last >= 0 && currX < x) {
                List<Integer> curr = new ArrayList<>(maxCt.get(last));
                int m = curr.size(), p = 0;
                while(p < m && currX < x) {
                    currSum += curr.get(p++) * last;
                    currX++;
                }
                last--;
            }
            ans[i] = currSum;
            // move window
            // remove nums[i]
            int currCt = mp.get(nums[i]);
            maxCt.get(currCt).remove(nums[i]);
            maxCt.get(currCt - 1).add(nums[i]);
            if (currCt == 1)    mp.remove(nums[i]);
            else mp.put(nums[i], currCt - 1);

            // add nums[i + k]
            if (i + k < n) {
                currCt = 0;
                if (mp.containsKey(nums[i + k])) {
                    currCt = mp.get(nums[i + k]);
                }
                if (maxCt.get(currCt) != null)
                    maxCt.get(currCt).remove(nums[i + k]);
                maxCt.get(currCt + 1).add(nums[i + k]);
                mp.put(nums[i + k], currCt + 1);
            }
        }
        return ans;
    }
}