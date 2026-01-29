class Solution {
    public ArrayList<Integer> asciirange(String s) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        int n = s.length();
        int[] pref = new int[n];
        int[][] ct = new int[26][2];
        for (int[] x : ct)  Arrays.fill(x, -1);
        pref[0] = (s.charAt(0) - 'a') + 97;

        for (int i = 0; i < n; i++) {
            pref[i] = ((i - 1) >= 0 ? pref[i - 1] : 0) + ((s.charAt(i) - 'a') + 97);
            int idx = s.charAt(i) - 'a';
            if (ct[idx][0] == -1)   ct[idx][0] = i;
            else ct[idx][1] = i;
            // System.out.println(s.charAt(i) + "::" + ct[idx][0] + "::" + ct[idx][1] + "::" + pref[i]);
        }

        for(int[] x : ct) {
            if (x[0] != -1 && x[1] != -1 && pref[x[1] - 1] - pref[x[0]] > 0) {
                ans.add(pref[x[1] - 1] - pref[x[0]]);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}