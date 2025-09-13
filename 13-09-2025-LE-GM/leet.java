class Solution {
    public int maxFreqSum(String s) {
        int[] ct = new int[26];
        int n = s.length(), maxV = 0, maxC = 0;
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i); 
            ct[curr - 'a']++;
            if (curr == 'a' || curr == 'e' ||curr == 'i' ||curr == 'o' ||curr == 'u')
                maxV = Math.max(maxV, ct[curr - 'a']);
            else maxC = Math.max(maxC, ct[curr - 'a']);
        }
        return maxV + maxC;
    }
}