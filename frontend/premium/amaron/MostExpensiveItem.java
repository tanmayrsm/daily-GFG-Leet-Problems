class Solution {
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        if (primeOne > primeTwo)    
            return mostExpensiveItem(primeTwo, primeOne);
        int l = primeOne + 1, r = Integer.MAX_VALUE, ans = l;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (solve(primeOne, primeOne, mid)) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
    private static boolean solve(int p1, int p2, int num) {
        if (num > p1 && num < p2 && num % p1 != 0)
            return false;
        int ct = 1;
        System.out.println((p2 * ct) + "::" + (num - p2*ct) + "::" + p1);
        while (p2 * ct < num && (num - p2 * ct) % p1 != 0) {
            ct++;
        }
        return p2 * ct >= num;  // you got a number which broke condition above
        // if p2 * ct < num => means u got something which was able to divide by p1
        // hence 'num' is not qualified number
    }
}