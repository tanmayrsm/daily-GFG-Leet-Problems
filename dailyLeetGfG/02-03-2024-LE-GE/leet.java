class Solution {
    public int[] sortedSquares(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for(int x : nums)   arr.add(x);
        return arr.stream()
                .sorted((a, b) -> Integer.compare(a*a, b * b))
                .map(a -> a*a)
                .mapToInt(a -> a)
                .toArray();
    }
}