class Solution {
    public static ArrayList<Integer> constructList(int q, int[][] queries) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        int finXor = 0, n = -1;
        for(int [] query : queries) {
            if(query[0] == 0)
                ans.add(query[1] ^ finXor);
            else
                finXor ^= query[1];
        }
        n = ans.size();
        for(int i = 0; i < n; i++) {
            ans.set(i, ans.get(i) ^ finXor);
        }
        Collections.sort(ans);

        return ans;
    }
}
