class Solution {

    public int minSteps(int m, int n, int d) {
        // If d is greater than both jugs
        if (d > Math.max(m, n)) return -1;

        // If d is not multiple of gcd(m, n)
        if (d % gcd(m, n) != 0) return -1;

        // Try both ways and take minimum
        return Math.min(pour(m, n, d), pour(n, m, d));
    }

    // Function to simulate pouring from one jug to another
    private int pour(int fromCap, int toCap, int d) {
        int from = fromCap; // fill from jug
        int to = 0;         // to jug initially empty
        int step = 1;       // initial fill counts as 1 step

        while (from != d && to != d) {
            // Pour water
            int transfer = Math.min(from, toCap - to);
            to += transfer;
            from -= transfer;
            step++;

            // Check if we got d
            if (from == d || to == d)
                break;

            // If from jug becomes empty, fill it
            if (from == 0) {
                from = fromCap;
                step++;
            }

            // If to jug becomes full, empty it
            if (to == toCap) {
                to = 0;
                step++;
            }
        }

        return step;
    }

    // Function to compute gcd
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}