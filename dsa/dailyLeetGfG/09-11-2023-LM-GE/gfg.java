
class Solution
{
    int columnWithMaxZeros(int arr[][], int N)
    {
        // code here 
        int ans = -1, maxzero = 0;
        for(int i = 0; i< N; i++){
            int ct = 0;
            for(int j = 0; j < N; j++){
                if (arr[j][i] == 0)
                    ct++;
            }
            if(ct >maxzero){
                 maxzero = ct;
                 ans = i;
            }
        }
        return ans;
    }
}