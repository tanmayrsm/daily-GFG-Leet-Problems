import java.util.HashSet;
import java.util.Set;

class Solution {
    private static Set<String> set;
    public int numTilePossibilities(String tiles) {
        // Input: tiles = "AAB"
        // Output: 8
        // Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
        // A, B, AA, AB, AAB
        // BA, BAA, ABA
        // A : 2, B : 1
        // 
        int n = tiles.length();
        int[] ct = new int[26];
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ct[tiles.charAt(i) - 'A']++;
        }
        solve(ct, new StringBuilder(), n, 0);
        return set.size();
    }
    private void solve(int[] ct, StringBuilder sb, int sLen, int sbLen) {
        if (sbLen == sLen) {
            return;
        }
        for (int i = 0;  i < ct.length; i++) {
            if (ct[i] > 0) {
                char curr = (char)(i + 'a');
                StringBuilder newer = new StringBuilder(sb);
                newer.append(sb + "" + curr + "");
                set.add(newer.toString());
                ct[i]--;
                solve(ct, newer, sLen, sbLen + 1);
                ct[i]++;
            }
        }
        
    }
}