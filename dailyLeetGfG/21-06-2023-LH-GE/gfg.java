
class Solution {
    static int sumOfNaturals(int n) {
        // code here
        // formula - n * (n + 1) / 2
        long r = (long)n * (long)(n + 1) % 1000000007;
        return (int)(r) / 2;
    }
};