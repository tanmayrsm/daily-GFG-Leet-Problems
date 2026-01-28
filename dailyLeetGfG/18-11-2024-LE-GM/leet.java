import java.util.*;

class Solution {
    int n;
    int sfind(int[] code, int k, int i) {
        int s = 0;
        for (int count = 0; count < k; count++) {
            i = (i + 1) % n;
            s += code[i];
        }
        return s;
    }

    int nsfind(int[] code, int k, int i) {
        int s = 0;
        for (int count = 0; count < Math.abs(k); count++) {
            i = (i - 1 + n) % n;
            s += code[i];
        }
        return s;
    }
    public int[] decrypt(int[] code, int k) {
        n = code.length;
        int[] out = new int[n];
        
        if (k == 0) return out;

        for (int i = 0; i < n; i++) {
            if (k > 0) {
                out[i] = sfind(code, k, i);
            } else if (k < 0) {
                out[i] = nsfind(code, k, i);
            }
        }
        
        return out;
    }
}