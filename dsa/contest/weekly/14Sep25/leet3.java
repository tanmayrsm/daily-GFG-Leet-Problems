class Solution {
    public int minTime(String s, int[] order, int k) {
        TreeSet<Integer> st = new TreeSet<>();
        int n = s.length();
        long total = (n * (n + 1)) / 2, invalid = total;
        st.add(-1);
        st.add(n);
        for (int i = 0; i < order.length; i++) {
            int o = order[i];
            st.add(o);
            long lower = st.lower(o);
            long upper = st.higher(o);
            long lowerInValidSub = getSub(lower, o);
            long upperInValidSub = getSub(o, upper);
            long prevInValid = getSub(lower, upper);
            invalid = invalid - prevInValid + lowerInValidSub + upperInValidSub;
            long valids = total - invalid;
            if (valids >= k)    return i;
        }
        return -1;
    }
    private long getSub(long x, long y) {
        if (x > y)  return getSub(y, x);
        long len = (y - x - 1);
        return (len * (len + 1)) / 2;
    }
}
// n = 8, totInv = 36
// (-1, 2) (2, 8) -> 2(3)/2 + 5(6)/2 -> 18
// --*-----


// --*--
// ans = (left + right + self + whole)

// --*--*--
// 2, 5 -> #(0,1) + #(3) + #(2) -> 9 (y)
// total sub -> 36
// valid -> 27


// 0 9 32 x 45
// y
// 32 - 45 ~ p
// 32 - x, x - 45
// q, t

// y - (p) + (q + t)

