// User function Template for Java
class Solution {
    int lps(String str) {
        // code here
        return calc(str);
    }
    private int calc(String str) {
        int m = str.length();
        int i = 0, j = 1, maxo = 0;
        int[] arr = new int[m];
        while(j < m) {
            if (j < m && str.charAt(i) == str.charAt(j)) {
                arr[j] = i + 1;
                i++;
                j++;
            } else {
                if(i - 1 >= 0)
                    i = arr[i - 1];
                else if (i == 0)
                    j++;
            }
        }
        return arr[m - 1];
    }
}