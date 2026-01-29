class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length, m = mat[0].length;
        TreeMap<Integer, TreeSet<Integer>> tm = new TreeMap<>();
        int[] noOfOnes = new int[n];
        int[] ans = new int[k];
        int p = 0;
        for(int[] x : mat) {
            int ct = 0;
            for(int i = 0; i < m && x[i] == 1; i++)
                ct++;
            noOfOnes[p++] = ct;
        }

        for(int i = 0; i < n; i++) {
            if(tm.containsKey(noOfOnes[i]))
                tm.get(noOfOnes[i]).add(i);
            else {
                tm.put(noOfOnes[i], new TreeSet<>(Arrays.asList(i)));
            }
        }

        p = 0;
        for(Map.Entry<Integer, TreeSet<Integer>> x : tm.entrySet()) { 
            for(Integer g : x.getValue())
                if(p < k)
                    ans[p++] = g;
            
        }

        return ans;
        

    }
}