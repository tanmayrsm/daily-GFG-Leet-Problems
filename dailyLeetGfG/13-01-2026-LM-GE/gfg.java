class Solution {
    public boolean canServe(int[] arr) {
        // code here
        int noOf5 = 0, noOf10 = 0, noOf20 = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            if(arr[i] == 5) noOf5++;
            else if (arr[i] == 10) {
                if(noOf5 == 0)  return false;
                noOf5--;
                noOf10++;
            } else {
                if(noOf10 > 0 && noOf5 > 0) {
                    noOf10--;
                    noOf5--;
                } else if (noOf5 >= 3) {
                    noOf5 -= 3;
                } else return false;
                noOf20++;
            }
        }
        return true;
    }
}