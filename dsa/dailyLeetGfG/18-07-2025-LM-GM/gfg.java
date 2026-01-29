class Solution {
    int lcmTriplets(int n) {
        // Small n can be answered immediately
        if (n <= 2) return n;      // {1} → 1, {2,1} → 2
        if (n == 3)  return 6;     // LCM(1,2,3) = 6

        // Case 1: n is odd → perfect trio n, n‑1, n‑2
        if (n % 2 == 1) {               // bit‑check for odd
            return n * (n - 1) * (n - 2);
        }

        // Case 2: n is even
        if (n % 3 != 0) {
            // Even but NOT divisible by 3 → use n, n‑1, n‑3
            return n * (n - 1) * (n - 3);
        }
        // Case 3: n is a multiple of 6 → step left to (n‑1, n‑2, n‑3)
        return (n - 1) * (n - 2) * (n - 3);
    }
}
/*
Intuition

I started by asking myself “Which three numbers ≤ n will give the largest possible LCM?”
Because the LCM of numbers that share fewer common factors is closer to their product, I quickly realised that I should look at the biggest numbers near n, but avoid picking three that all share 2 or 3 as a factor.
After testing small values by hand, a clear pattern popped out:

n	best triplet	max LCM
4	4, 3, 1	12
6	5, 4, 3	60
7	7, 6, 5	210
8	8, 7, 5	280
9	9, 8, 7	504
Whenever n is odd, choosing n, n‑1, n‑2 is perfect because at least two of them are coprime, so their LCM is just their product.
When n is even, I need to dodge common factors of 2 and 3:

If n is even but not a multiple of 3, the trio n, n‑1, n‑3 works best.
If n is a multiple of 6 (even and divisible by 3), I slide one step left and use (n‑1, n‑2, n‑3).
Everything else collapses into these three neat cases.
*/