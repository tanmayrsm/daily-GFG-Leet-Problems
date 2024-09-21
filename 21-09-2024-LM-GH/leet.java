class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
        Collections.sort(ans, (Integer a, Integer b) -> {
            return String.valueOf(a).compareTo(String.valueOf(b));
        });
        return ans;
    }
}