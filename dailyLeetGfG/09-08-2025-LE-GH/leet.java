class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n < 0)  return false;
        String x = Integer.toBinaryString(n);
        long count = x.chars().filter(ch -> ch == '1').count();
        return count == 1;
    }
}