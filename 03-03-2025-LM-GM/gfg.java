class Solution {

    public ArrayList<Integer> longestSubarray(int[] arr, int x) {
        // code here
          TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        int left = 0, maxLen = 0, startIdx = 0;

        for (int right = 0; right < arr.length; right++) {
            freqMap.put(arr[right], freqMap.getOrDefault(arr[right], 0) + 1);

            while (freqMap.lastKey() - freqMap.firstKey() > x) {
                freqMap.put(arr[left], freqMap.get(arr[left]) - 1);
                if (freqMap.get(arr[left]) == 0) freqMap.remove(arr[left]);
                left++;
            }

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                startIdx = left;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = startIdx; i < startIdx + maxLen; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}