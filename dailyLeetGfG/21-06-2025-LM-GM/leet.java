import java.util.TreeSet;

class Solution {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        int[] ct = new int[26];
        TreeSet<Integer> st = new TreeSet<>();
        for (int i = 0; i < n; i++)
            ct[word.charAt(i) - 'a']++;
        for(int x : ct) st.add(x);
        return solve(st, k);
    }
    private int solve(TreeSet<Integer> st, int k) {
        if (st.isEmpty())   return  0;
        int first = st.first(), last = st.last();
        if (last - first <= k)  return k;
        int diff = (last - first) - k;

        int takeLast = Integer.MAX_VALUE, takeFirst = Integer.MAX_VALUE;
        if (last - diff >= 0) {
            st.remove(last);
            st.add(last - diff);
            takeLast = diff + solve(st, k);
            st.remove(last - diff);
            st.add(last);
        } else {
            st.remove(last);
            takeLast = last + solve(st, k);
            st.add(last);
        }
        st.remove(first);
        takeFirst = first + solve(st, k);
        st.add(first);
        return Math.min(takeFirst, takeLast);
    }
}