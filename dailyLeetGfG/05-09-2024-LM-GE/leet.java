import java.util.Arrays;

class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        long sum = Arrays.stream(rolls).mapToLong(i -> i).sum();
        int len = n + rolls.length, i = 0;
        long rem = mean * len - sum;
        int[] ans = new int[n];
        int addition = (int)rem % n;
        int ordNo = (int)rem / n;
        if (rem  > 6 * n || rem <= 0 || ordNo == 0)  return new int[0];
        while(rem > 0 && i < ans.length) {
            int finalAddition = ordNo;
            if (addition > 0) {
                finalAddition++;
                addition--;
            }
            rem -= finalAddition;
            ans[i++] = finalAddition;
        }
        return ans;
    }
}

// 24 + X = 1 * 12
// rem = 12
// 