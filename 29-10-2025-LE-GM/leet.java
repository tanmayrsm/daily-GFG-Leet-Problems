class Solution {
    public int smallestNumber(int n) {
        String len = Integer.toBinaryString(n);
        String newBin = "1".repeat(len.length());
        return Integer.parseInt(newBin, 2);
    }
}