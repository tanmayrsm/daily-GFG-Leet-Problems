class Solution {
    public int findClosest(int x, int y, int z) {
        if (Math.abs(z - y) < Math.abs(z - x))  return 2;
        else if (Math.abs(z - y) > Math.abs(z - x)) return 1;
        return 0;
    }
}