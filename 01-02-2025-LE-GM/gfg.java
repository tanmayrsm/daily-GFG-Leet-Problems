class Solution 
{
    static public boolean isWordExist(char[][] mat, String word) 
    {
        // Code here
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                if(mat[i][j]==word.charAt(0)) 
                {
                    if(dfs(mat,i,j,word,0)) return true;
                }
            }
        }
        return false;
    }
    private static boolean dfs(char[][] mat,int i, int j, String word , int ind)
    {
        if(ind==word.length()) return true;
        if(i<0 || i>=mat.length || j<0 || j>=mat[0].length || mat[i][j] != word.charAt(ind)) return false;
        char temp=mat[i][j];
        mat[i][j]='#';
        boolean f= dfs(mat,i+1,j,word,ind+1) || dfs(mat,i-1,j,word,ind+1) ||dfs(mat,i,j+1,word,ind+1) ||dfs(mat,i,j-1,word,ind+1);
        mat[i][j]=temp;
        return f;
    }
}