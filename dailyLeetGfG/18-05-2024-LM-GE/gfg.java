class Solution {
    public int findPeakElement(List<Integer> a) {
        // Code here
        int max = 0;
        for(Integer num : a)
            max = Math.max(max, num);
        return max;
    }
}