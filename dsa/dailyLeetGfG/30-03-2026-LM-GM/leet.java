class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[] oddS1  = new int[26];
        int[] evenS1 = new int[26];
        int[] oddS2  = new int[26];
        int[] evenS2 = new int[26];
        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                evenS1[s1.charAt(i) - 'a']++;
                evenS2[s2.charAt(i) - 'a']++;
            } else {
                oddS1[s1.charAt(i) - 'a']++;
                oddS2[s2.charAt(i) - 'a']++;
            }
        }
        return isEqual(evenS1, evenS2) && isEqual(oddS1, oddS2);
    }
    private boolean isEqual(int[] a, int[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            if(a[i] != b[i])
                return false;
        return true;
    }
}