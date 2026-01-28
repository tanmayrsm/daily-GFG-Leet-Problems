import java.util.Arrays;

class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : nums)
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(numsBoxed, (a, b) -> {
            if (mp.get(a).equals(mp.get(b))) {
                return Integer.compare(b, a); // Sort by value in descending order if frequencies are the same
            } else {
                return Integer.compare(mp.get(a), mp.get(b)); // Sort by frequency
            }
        });
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsBoxed[i];
        }
        
        return nums;

    }
}