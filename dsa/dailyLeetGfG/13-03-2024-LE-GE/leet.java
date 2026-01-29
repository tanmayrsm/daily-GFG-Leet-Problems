class Solution {
    public int pivotInteger(int n) {
        // [1,2,3,4, 5, 6, 7, 8 ]
        // [1,3,6,10,15,21,28,36]
        
        // 8 -> 36    , 8
        // 7 -> 36 - 8, 7 + 8
        // 6 -> 28 - 7, 6 + 7 + 8

        int sum = n * (n + 1) / 2, left = sum, right = n;
        for(int j = n; j >= 1; j--) {
            if(left == right)   return j;
            left -= j;
            right += (j - 1);   // as per above manipulation in example
        }

        return -1;
    }
}

// best ans -> if n is sq root of some no, pivot = Math.sqrt(n)