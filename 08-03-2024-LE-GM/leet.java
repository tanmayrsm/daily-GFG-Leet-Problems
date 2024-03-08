class Solution {
    public int maxFrequencyElements(int[] nums) {
        int maxFreq = 0, ct = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for(int x : nums) {
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            maxFreq = Math.max(maxFreq, mp.get(x));
        }
        for(Map.Entry<Integer, Integer> d : mp.entrySet())
            if(d.getValue() == maxFreq) 
                ct++;
        return ct * maxFreq;
    }
}