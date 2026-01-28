class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : nums) {
            if (!mp.containsKey(num)) {
                mp.put(num, getMapped(num, mapping));
            }
        }

        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        // for(int i : numsBoxed)
        // System.out.println("::" + mp);
        Arrays.sort(numsBoxed, (a, b) -> {
            if (mp.get(a).equals(mp.get(b))) {
                return 0;
            } else {
                return Integer.compare(mp.get(a), mp.get(b)); // Sort by frequency
            }
        });
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsBoxed[i];
        }
        
        return nums;
    }

    private static int getMapped(int num, int[] mapping) {
        StringBuilder sb = new StringBuilder();
        do {    // if num == 0, still I want mapping[0] to occur
            sb.append(mapping[num % 10] + "");
            num /= 10;
        } while(num > 0);
        if (sb.isEmpty())   return 0;
        return Integer.parseInt(sb.reverse().toString());
    }
}