class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length(), vow = 0;
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (curr == 'a' || curr == 'e' || curr == 'i' || curr == 'o' || curr == 'u')
                vow++;
        }
        if (vow == 0)   return false;
        if (vow % 2 == 1)   return true;
        return vow % 2 == 0;
    }
}
// o + e -> o
// if there are total odd no of vowels, no way Alice could win