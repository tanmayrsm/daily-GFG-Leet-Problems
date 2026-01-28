class Solution {
    public int[] sortByBits(int[] arr) {
        arr = Arrays.stream(arr).boxed().sorted((e1, e2) -> {
            int ct1 = count(e1);
            int ct2 = count(e2);
            if(ct1 == ct2)
                return Integer.compare(e1, e2);
            return Integer.compare(ct1, ct2);
        }).mapToInt(e -> e).toArray();
        return arr;
    }

    private static int count(int x) {
        int ct = 0;
        while(x > 0) {
            x = x & (x - 1);
            ct++;
        }
        return ct;
    }
}
