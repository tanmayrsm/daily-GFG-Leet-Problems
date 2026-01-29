class Solution {
    public void sortIt(int[] arr) {
        int[] sorted = Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> {
                    if (a % 2 == 0 && b % 2 == 0) return Integer.compare(a, b);
                    else if (a % 2 == 1 && b % 2 == 1) return Integer.compare(b, a);
                    else if (a % 2 == 1 && b % 2 == 0) return -1;
                    return 1;
                })
                .mapToInt(Integer::intValue)
                .toArray();

        System.arraycopy(sorted, 0, arr, 0, arr.length);
    }
}