class Solution {
    public int maxProduct(int[] nums) {
        int firstMax = 0, secondMax = 0;
        for(int x : nums) {
            if(x >= firstMax) {
                secondMax = firstMax;
                firstMax = x;
            } else if (x >= secondMax) {
                secondMax = x;
            }
        }
        return (firstMax - 1) * (secondMax - 1);
    }
}