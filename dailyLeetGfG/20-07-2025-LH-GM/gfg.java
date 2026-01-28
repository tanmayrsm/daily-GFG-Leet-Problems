class Solution {
    public int countValid(int n, int[] arr) {
        // code here
        long totalNumbers = 9 * (long) Math.pow(10, n - 1);
        // 9 10 10...n

        HashSet<Integer> forbiddenDigits = new HashSet<>();
        for (int digit : arr) {
            forbiddenDigits.add(digit);
        }

        int allowedCount = 10 - forbiddenDigits.size();
        long invalidNumbers;

        if (forbiddenDigits.contains(0)) {
            invalidNumbers = (long) Math.pow(allowedCount, n);
        } else {
            invalidNumbers = (long) (allowedCount - 1) * (long) Math.pow(allowedCount, n - 1);
        }

        long result = totalNumbers - invalidNumbers;
        return (int) result;
    }
}