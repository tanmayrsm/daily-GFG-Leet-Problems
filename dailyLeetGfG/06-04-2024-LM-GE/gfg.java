
class Solution {
    // Function to count number of ways to reach the nth stair
    // when order does not matter.
    Long countWays(int n) {
        // your code here
        return (long)(n/2 + 1);
    }
}

// Fibo series =>>>

// 1 => 1 .........1
// 2 => 1,1 | 2 ..........2
// 3 => 1,1,1 | 2,1 | 1,2 .............3
// 4 => 1,1,1,1 | 2,1,1 | 1,2,1 | 1,1,2 | 2,2 ..............5
// 5 => 1,1,1,1,1 | 1,2,1,1 | 2,1,1,1 | 1,1,2,1 | 1,1,1,2 | 2,2,1 | 2,1,2 | 1,2,2 ....................8

// 1 => 1
// 2 => 2
// 3 => 2
// 4 => 3
// 5 => 3
// 6 => 4
// 7 => 4
// 8 => 5