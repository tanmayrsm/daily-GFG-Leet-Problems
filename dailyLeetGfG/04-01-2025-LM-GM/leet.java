class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length(), ans = 0;
        int[] uniq = new int[n];

        int[] start = new int[26];
        Arrays.fill(start, -1);
        int[] end = new int[26];
        int[] charCt = new int[26];

        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - 'a';
            if (start[curr] == -1)
                start[curr] = i;
            end[curr] = i;
        }

        for (int i = 0; i < start.length; i++) {
            if (start[i] > -1 && end[i] - start[i] > 1) {
                Set<Character> st = new HashSet<>();
                // System.out.println(start[i] + "::" + end[i]);
                for(int j = start[i] + 1; j < end[i]; j++)
                    st.add(s.charAt(j));
                ans += st.size();
            }
        }

        return ans;
    }
}