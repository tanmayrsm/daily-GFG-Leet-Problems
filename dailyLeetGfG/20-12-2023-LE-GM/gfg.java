
class Solution{
    static int findWinner(int n, int A[]){
        // code here       
        int val = 0;
        if(n % 2 == 0)  return 1;
        for(int x : A)
            val ^= x;
        if(val == 0)    return 1;
        return 2;
    }
}