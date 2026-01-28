class Solution {
    // https://www.youtube.com/watch?v=3g4loToGuAk
    // not complete soln in vid above though
    int getLPSLength(String str) {
        // code here
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