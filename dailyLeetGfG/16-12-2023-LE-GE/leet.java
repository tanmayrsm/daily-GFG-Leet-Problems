class Solution {
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);    Arrays.sort(t1);
        return String.valueOf(s1).equals(String.valueOf(t1));
    }
}