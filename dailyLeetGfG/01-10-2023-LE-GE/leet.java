class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder("");
        String[] ss = s.split(" ");
        for(String r : ss) {
            StringBuilder k = new StringBuilder(r);
            sb.append(" ");
            sb.append(k.reverse());
        }
        return sb.substring(1).toString();  // substr(1), cus I dont want the leading " " which I entered in for-loop
    }
}