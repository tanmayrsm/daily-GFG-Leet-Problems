class Solution {
    public boolean winnerOfGame(String s) {
        int aliceCanPlay = 0, bobCanPlay = 0, n = s.length();
        for(int i = 1; i < n - 1; i++) {
            if(s.charAt(i) == 'A' && s.charAt(i - 1) == 'A' && s.charAt(i + 1) == 'A')
            aliceCanPlay++;
            else if (s.charAt(i) == 'B' && s.charAt(i - 1) == 'B' && s.charAt(i + 1) == 'B')
            bobCanPlay++;
        }

        return aliceCanPlay > bobCanPlay;
    }
}