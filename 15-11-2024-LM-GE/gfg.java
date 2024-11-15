
class Solution {
    public int getSecondLargest(int[] arr) {
        // Code Here
        int max = Arrays.stream(arr).max().orElse(-1);
        return Arrays.stream(arr)
            .filter(i -> i != max).max().orElse(-1);
    }
}