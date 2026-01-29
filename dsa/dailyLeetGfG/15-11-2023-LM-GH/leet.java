class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int maxo = 1;
        arr[0] = maxo;
        for(int i = 1; i < arr.length; i++) {
            if(Math.abs(arr[i] - arr[i - 1]) > 1)
                arr[i] = arr[i - 1];
            maxo = Math.max(maxo, arr[i]);
        }
        return maxo;
    }
}