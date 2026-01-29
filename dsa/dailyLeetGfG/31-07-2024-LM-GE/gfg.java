
class Solution {
    public String longestCommonPrefix(String arr[]) {
        // code here
        StringBuilder sb = new StringBuilder();
        int curr = 0;
        while(true) {
            boolean match = true;
            char currChar = '#';
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() == curr || (currChar != '#' && arr[i].charAt(curr) != currChar)) {
                    match = false;
                    break;
                }
                if (currChar == '#')
                    currChar = arr[i].charAt(curr);
            }
            if (!match) break;
            else sb.append(currChar + "");
            curr++;
            // if (curr >= arr.length) break;
        }
        if (sb.toString().isEmpty())   return "-1";
        return sb.toString();
    }
}