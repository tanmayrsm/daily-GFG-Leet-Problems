class Solution {
    String removeSpaces(String s) {
        // code here
        int n = s.length();
        int i = 0, itr = 0;
        char[] arr = s.toCharArray();
        while (i < n) {

            if (arr[i] != ' ') {

                arr[itr++] = arr[i];
            }
            i++;
        }

        return new String(arr, 0, itr);
    }
}