class Solution {
    public int minOperations(String s) {
        int n = s.length(), ct1 = 0, ct0 = 0;
        boolean is1 = false;
        boolean is0 = false;
        for (int i = 0; i < n; i++) {
            is1 = (i % 2 == 0);
            is0 = (i % 2 == 1);

            char curr = s.charAt(i);
            if((is1 && curr == '0') || (!is1 && curr == '1'))  ct1++;
            if((is0 && curr == '0') || (!is0 && curr == '1'))  ct0++;

        }
        return Math.min(ct1, ct0);
    }
}

