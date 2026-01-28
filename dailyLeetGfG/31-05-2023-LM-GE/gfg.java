class Solution {
    // Function to find largest number with minimum frequency
    public static int LargButMinFreq(int arr[], int n) {
        // Your code here
        Map<Integer, Integer> freq = new HashMap<>();
        for(int x : arr) {
            if(freq.get(x) == null)
                freq.put(x, 1);
            else    freq.put(x, freq.get(x) + 1);
        }
        int minFreq = Integer.MAX_VALUE;
        int maxo = -1;
        for(Map.Entry<Integer, Integer> x : freq.entrySet()) {
            if(x.getValue() <= minFreq) {
                minFreq = x.getValue();
                maxo = Math.max(maxo, x.getKey());
            }
        }
        return maxo;
    }
}