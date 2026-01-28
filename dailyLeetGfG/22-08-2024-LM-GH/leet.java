class Solution {
    public int findComplement(int num) {
        int complement = 0, k = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                // take it as '1'
                complement += Math.pow(2, k);
                k++;
            } else {
                // take it as '0'
                k++;
            }
            num /= 2;
        }
        return complement;
    }
}
