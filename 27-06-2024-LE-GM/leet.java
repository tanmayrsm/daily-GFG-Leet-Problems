class Solution {
    public int findCenter(int[][] edges) {
        int n = edges.length + 1;
        int[] inorder = new int[n];
        for(int[] edge : edges) {
            inorder[edge[0] - 1]++;
            inorder[edge[1] - 1]++;
        }

        for(int i = 0; i < n; i++)
            if (inorder[i] == n - 1)
                return i + 1;
                
        return -1;
    }
}