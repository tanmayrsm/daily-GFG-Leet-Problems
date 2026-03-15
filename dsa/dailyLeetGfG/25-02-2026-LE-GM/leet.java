class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] boxed = new Integer[n];
        for (int i = 0; i < n; i++) {
            boxed[i] = arr[i];
        }

        // Step 2: sort with custom comparator
        Arrays.sort(boxed, (a, b) -> {
            int bitsA = Integer.bitCount(a);
            int bitsB = Integer.bitCount(b);

            if (bitsA == bitsB) {
                // tie-break by value
                return Integer.compare(a, b);
            }
            return Integer.compare(bitsA, bitsB); // ascending by set bits
        });

        for (int i = 0; i < n; i++) {
            arr[i] = boxed[i];
        }

        return arr;
    }
}