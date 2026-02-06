class Solution {
    public int getLastMoment(int n, int left[], int right[]) {
        // code here
        int max = 0;
        for (int x : left)  max = Math.max(max, x);
        for (int x : right) max = Math.max(max, n - x);
        return max;
    }
}
/*
Input: n = 4, left[] = [2], right[] = [0, 1, 3]
Output: 4

[0 1 -2 3] t = 0
[1 -1 2 4] t = 1
[-1 1 3 -] t = 2
[-0 2 4 -] t = 3
[ - 3 - -] t = 4

It looks really intimidating at first,
but if say two ants collide and they continue moving in same direction...thats all about this problem
*/
