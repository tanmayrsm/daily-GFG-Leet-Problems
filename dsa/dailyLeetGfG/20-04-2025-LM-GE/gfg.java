// User function Template for Java
class Solution {
    public int findDuplicate(int[] arr) {
        // code here
         if (arr.length == 2) return arr[0];

        int n = arr.length - 1;
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return (int) (actualSum - expectedSum);
    }
}