class Solution {
    public int countOdds(int low, int high) {
        if (low > 0 && low % 2 == 1)  low--;
        if (high > 0 && high % 2 == 1)  high++;
        return (high - low) / 2;
    }
}

// 4 5 6 7 8 9 10
// (4-10), (5-10), (5-9)


