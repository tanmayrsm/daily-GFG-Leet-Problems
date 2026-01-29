class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxVal = Arrays.stream(nums).max().getAsInt() + k;
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num , freq.getOrDefault(num , 0) + 1);

            int l = Math.max(num - k , 0);
            int r = Math.min(num + k , maxVal);

            diff.put(l , diff.getOrDefault(l , 0) + 1);
            diff.put(r + 1 , diff.getOrDefault(r + 1 , 0) - 1);

            diff.putIfAbsent(num , diff.getOrDefault(num , 0));
        }

        int res = 1;
        int cumuSum = 0;

        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int target = entry.getKey();
            int delta = entry.getValue();
            cumuSum += delta;

            int targetFreq = freq.getOrDefault(target , 0);
            int toConvert = cumuSum - targetFreq;
            int maxPossFreq = Math.min(toConvert , numOperations);
            res = Math.max(res , targetFreq + maxPossFreq);
        }
        return res;
    }
}