class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length(), yes = 0, currY = 0, penalty = Integer.MAX_VALUE, minIdx = penalty;
        for (int i = 0; i < n; i++)
            if (customers.charAt(i) == 'Y')
                yes++;

        for (int i = 0; i < n; i++) {
            int rhs = yes - currY; // calculate total Ys on RHS including i, if shop closed at i
            int lhs = i - currY; // total N at lhs excluding i
            if (customers.charAt(i) == 'Y')
                currY++;
            if(lhs + rhs < penalty) {
                penalty = lhs + rhs;
                minIdx = i;
            }
        }
        int lhs = n - currY;
        if(lhs < penalty) {
            penalty = lhs;
            minIdx = n;
        }

        return minIdx;
    }
}