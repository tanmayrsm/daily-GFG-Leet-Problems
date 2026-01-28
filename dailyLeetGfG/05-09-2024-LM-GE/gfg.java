
// User function Template for Java
class Solution {

    // Note that the size of the array is n-1
    int missingNumber(int n, int arr[]) {

        // Your Code Here
        long sum = Arrays.stream(arr).mapToLong(i -> i).sum();
        long N = n;
        long expectedSum = (N * (N + 1L)) / 2L;
        return (int)(Math.abs(expectedSum - sum));
    }
}