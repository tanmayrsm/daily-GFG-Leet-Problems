class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s; // doubled = aba + aba = abaaba;
        String removeFirstAndLast = doubled.substring(1, doubled.length() - 1); // = baab
        return removeFirstAndLast.contains(s);  // false
    }
}