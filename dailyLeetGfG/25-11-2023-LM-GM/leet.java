class Solution {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length, sum = 0;
        int[] pref = new int[n + 1];    // to pad a 0 at start, else will have to put (i-1) >= 0 check everywhere

        int[] ans = new int[n];
        pref[1] = nums[0];
        for(int i = 1; i < n; i++)
            pref[i + 1] = pref[i] + nums[i];
        sum = pref[n];

        for(int i = 0; i < n; i++) {
            int curr = (sum - pref[i]) - nums[i]*(n - i);   // sum of elements post i
            if(i - 1 >= 0)
                curr += Math.abs(pref[i] - nums[i] * i);    // sum of element before i
            ans[i] = curr;
        }

        return ans;
    }

    
    // how I came up to below approach ?
    // [1,4,6,8,10]      = 29...overall sum
    // [24,15,13,15,21]  ...required 

    // prefix = [1,5,11,19,29]

    // first elem =
    // 29 - 1(5) = 24

    // second elem -> i = 1
    // [_,0,2,4,6]
    // 29 - 1 = 28  
    // 28 - 4(4) = 12   ....post i sum //  [_,0,2,4,6]
    // 12 + |1 - 4(1)| = 15     ..... pre i sum = 1, so we get  // [3, 0, _, _, _]

    // third elem -> i = 2
    // 29 - 5 = 24
    // 24 - 6(3) = 6    ..... post i sum
    // 6 + |5 - 6(2)| = 13  ..... pre i sum = 5

    // fourth elem -> i = 3
    // 29 - 11 = 18
    // 18 - 8(2) = 2    .... post i sum
    // 2 + |11 - 8(3)| = 15     .... pre i sum = 11
}