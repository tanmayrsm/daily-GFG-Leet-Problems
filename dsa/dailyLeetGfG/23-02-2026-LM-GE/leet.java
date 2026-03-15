class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<Integer> st = new HashSet<>();
        int n = s.length();
        if(k > n)   return false;
        int curr = Integer.parseInt(s.substring(0, k), 2);
        int mask = (1 << k) - 1; // e.g. 0xFF for N = 8
        st.add(curr);
        for(int i = k; i < n; i++) {
            curr <<= 1;
            curr &= mask;   // discard left most bit
            curr |= (s.charAt(i) == '1' ? 1 : 0);   // add 01 at right most
            // System.out.println(i + "::" + curr);
            st.add(curr);
        }
        System.out.println(st);
        return st.size() == Math.pow(2, k);

    }
}

