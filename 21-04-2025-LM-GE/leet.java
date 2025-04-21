class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int n = differences.length;


        long min = 0, max = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            sum += differences[i];
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }

        int ans = (int)((upper - lower) - (max - min) + 1);
        return Math.max(0, ans);
    }
}

// proof of my logic ->
// 1, -3, 4
// x, x + 1, x - 2, x + 2
// min -> x - 2 = 1
// max -> x + 2 = 6
// x -> 3
// x -> 4

// 3, -4, 5, 1, -2
// x, x + 3, x - 1, x + 4, x + 5, x + 3
// min -> x - 1 = -4
// max -> x + 5 = 5
// x -> -3
// x -> 0

// -40
// x, x - 40
// min -> x - 40 = -46, x = -6
// max -> x = 53
// 53 - (-6)