class Solution {
    public static String caseSort(String s) {
        // code here
        int[] lowerChars = new int[26];
        int[] upperChars = new int[26];
        int n = s.length(), l = 0, r = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c))
                upperChars[c - 'A']++;
            else lowerChars[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (Character.isUpperCase(curr)) {
                while (upperChars[l] == 0)  l++;
                char newer = (char)('A' + l);
                sb.append(newer + "");
                upperChars[l]--;
            } else {
                while (lowerChars[r] == 0)  r++;
                char newer = (char)('a' + r);
                sb.append(newer + "");
                lowerChars[r]--;
            }
        }
        return sb.toString();
    }
}