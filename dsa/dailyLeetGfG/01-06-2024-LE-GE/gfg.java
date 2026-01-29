
class Solution {
    public static String oddEven(String s) {
        // code here
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int x = 0, y = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                if ((i + 1) % 2 == 0 && freq[i] % 2 == 0) {
                    x++;
                }
                if ((i + 1) % 2 != 0 && freq[i] % 2 != 0) {
                    y++;
                }
            }
        }

        return (x + y) % 2 == 0 ? "EVEN" : "ODD";
    }
}


// Given a string s of lowercase English characters, find out whether the summation of x and y is even or odd, where x is the count of distinct characters that occupy even positions in English alphabets and have even frequency, y is the count of distinct characters which occupy odd positions in English alphabets and have odd frequency.

// Note: Positive means greater than zero.

// Example 1:

// Input: 
// s = "abbbcc"
// Output: 
// ODD
// Explanation: 
// x = 0 and y = 1 so (x + y) is ODD. 'a' occupies 1st place(odd) in english alphabets and its frequency is odd(1), 'b' occupies 2nd place(even) but its frequency is odd(3) so it doesn't get counted and 'c' occupies 3rd place(odd) but its frequency is even(2) so it also doesn't get counted.