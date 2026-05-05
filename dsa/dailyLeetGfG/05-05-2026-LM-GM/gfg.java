class Solution {
    public long sumXOR(int[] arr) {
        // code here
        long ans = 0;
        int n = arr.length;
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