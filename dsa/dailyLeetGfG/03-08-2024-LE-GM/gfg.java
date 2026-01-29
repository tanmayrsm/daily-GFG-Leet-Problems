
// User function Template for Java

class Solution {
    // Function to find if there is a celebrity in the party or not.
    public int celebrity(int mat[][]) {
        // code here
        int n = mat.length;
        int[] inorder = new int[n];
        Arrays.fill(inorder, -1);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (inorder[i] == -1)   inorder[i]++;
                    inorder[i]++;
                    if(inorder[j] == -1)    inorder[j]++;
                }
            }
        for (int i = 0; i < n; i++)
            if (inorder[i] == 0)
                return i;
        return -1;
    }
}