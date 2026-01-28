class Solution {
    public String reversePrefix(String word, char ch) {
        int res = word.indexOf(ch);
        if(res <= 0) return word;
        StringBuilder sb = new StringBuilder(word.substring(0, res + 1));
        sb.reverse();
        return sb.toString() + word.substring(res + 1);
    }
}