class Solution {
    public int andInRange(int l, int r) {
        // Code here
        int shift = 0;



        // find common prefix

        while (l < r) {

            l >>= 1;

            r >>= 1;

            shift++;

        }



        // restore shifted bits

        return l << shift;


    }
}

// 1000
// 1101

// do l & r, rest find nos in that range having set bits at that posn
// 10001001

// l =   01101
// r =   11001
// res = 01001



