class Solution {
    public int countPermutations(int[] arr) {
        int shortest = arr[0], n = arr.length;
        for (int i = 1; i < n; i++)
            if (arr[i] <= shortest)
                return 0;
        // calculate n!
        long fact = 1;
        for (int i = 2; i < n; i++) {
            fact = (fact * i) % 1_000_000_007;
        }
        return (int)fact;
    }
}
