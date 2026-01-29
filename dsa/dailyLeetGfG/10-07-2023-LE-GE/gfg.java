
//User function Template for Java
class Solution
{
    public void transpose(int n,int a[][])
    {
        int k = 0;
        for(int i = 0; i < n; i++)
            for(int j = i; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
    }
}