class Solution {
    public static int findUnion(int a[], int b[]) {
        // code here
        Set<Integer> st = new HashSet<>();
        for (int x : a) st.add(x);
        for (int x : b) st.add(x);
        return st.size();
    }
}