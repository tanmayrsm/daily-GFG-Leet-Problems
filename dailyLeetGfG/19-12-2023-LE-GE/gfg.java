
class Solution
{
    //Function to find the first position with different bits.
    public static int posOfRightMostDiffBit(int m, int n)
    {
            
        // Your code here        
        int newNo = m ^ n;
        int idx = 1;
        while(newNo > 0) {
            if(newNo % 2 == 1)  return idx;
            newNo /= 2;
            idx++;
        }
        return -1;
    }
}

