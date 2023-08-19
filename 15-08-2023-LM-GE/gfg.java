
class Solution {

    public static int maxOnes(int a[], int n) {
        // how I came to below approach - 
        // 1 0 0 0 1 0 0 0 1 0 0 0 0 1 0 0 0  0
        // - 1 2 3 2 3 4 5 4 5 6 7 8 7 8 9 10 11
        // so given arr has - 14 0s and 4 1s
        // I can convert all max 14 0s to 1s, but I need to convert (14 - 11) = 3 1s into 0s in this process
        // new 1 count = old1s - converted 1s to 0s + (new 1s which came from 0s)
        //             = 4 - 3 + 14
        //             = 15
        // applying flip - from 1 to 11
        // 1 1 1 1 0 1 1 1 0 1 1 1 1 0 1 1 1 1
        // == 15 1s

        int ones = 0, converted = 0, maxConverted = 0;
        for(int x : a) {
            if(x == 1) {
                if(converted > 0)
                    converted--;
                ones++;
            }
            else converted++;
            maxConverted = Math.max(maxConverted, converted);
        }
        return ones + maxConverted;
    }
}