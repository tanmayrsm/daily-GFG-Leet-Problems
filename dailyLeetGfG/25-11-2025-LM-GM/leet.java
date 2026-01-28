class Solution {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) return -1;
        int step = 1, curr = 1;
        while(step < Integer.MAX_VALUE) {
            if (curr % k == 0)  return step;
            curr = curr % k;
            curr = curr * 10 + 1;
            step++;
        }
        return -1;

    }
}