
class Solution {
    static int singleElement(int[] arr , int N) {   // referred
        // code here
         int res=0;
        for(int i=0;i<32;i++)
        {    int flag=0;
            for(int j=0;j<N;j++)
            {
                flag+=((arr[j])>>i)&1;
                
            }
            flag=flag%3;
            res+=flag<<i;
        }
        return res;
    }
}