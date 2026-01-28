class Solution {
    public int findFinalValue(int[] nums, int original) {
        int res = 0;
        boolean origFound = false;
        for (int num : nums) {
            if (!origFound && num == original) origFound = true;
            if (num % original == 0) {
                int n = num / original;
                if ((n & (n - 1)) == 0) { // power of 2
                    res = res | n;
                }
            }
        }
        if (!origFound) return original;
        int rightMostSetBit = ~res & (res + 1); // formula for rightMostSetBit with power of 2
        return original * rightMostSetBit;
    }
}

3 6 12 24
3 (1 2 4 8)


[5,3,6,1,12]
-1 1 2 -1 4
-1 0 1 -1 2

000
001
010


if u or 1,4,16, wont it say 0 missing ?, wont say for 0, but surely say for 2 missing
00001
00100
10000

