
class Solution{
   
    // Function for finding maximum and value pair
    public long sumXOR (int arr[], int n) {
        //Complete the function
        
        // BIT MANIPULATION
        
        // for each bit from 0 to 31, find no of set bits and no of unset bits at position 'i'
        // ans += 2^(i) * (setBits at i * unsetBits at i)
        long ans = 0;
        for(int i = 0; i <= 31; i++) {
            long setBits = 0, unsetBits = 0;
            for(int j = 0; j < n; j++) {
                int isSet = arr[j] & (int)Math.pow(2, i);
                if(isSet != 0)  setBits++;
                else unsetBits++;
            }
            long possibleCombinations = setBits * unsetBits;
            ans += Math.pow(2, i) * possibleCombinations;
        }
        
        return ans;
    }
}