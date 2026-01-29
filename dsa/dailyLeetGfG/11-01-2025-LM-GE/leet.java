class Solution {
    public boolean canConstruct(String s, int k) {
        int[] ct = new int[26];
        int n = s.length(), odd = 0;
        if (k > n)  return false;
        for(int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;
        for(int x : ct) {
            if (x > 0) {
                if (x % 2 != 0) odd++;
            }
        }
        return odd <= k;
    }
}
// one character palindorme
// 3 character palindrome
// x length, where x is even
// I have to use all characters
// a :2, n : 2, b : 1, e : 2, l : 2
// l : 1, e : 3, t,c,d,o : 1, min => 6 (count of abs(odd - even))

// conclusion - minimum I need to create 'count of odd' no of separate palindrome