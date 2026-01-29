class Solution {
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {
        // code here
        int n = mat.length;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            ArrayList<Integer> curr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                curr.add(mat[i][j]);
            }
            ans.add(curr);
        }
        return ans;
    }
}