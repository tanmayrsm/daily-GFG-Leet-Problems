import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canArrange(int[] arr, int k) {
        int n = arr.length;
        int[] kArr = new int[k];
        for(int i = 0; i < n; i++) {
            if(arr[i] > 0) {
                arr[i]  %= k;
            } else if (arr[i] < 0) {
                int m = Math.abs(arr[i]) % k;
                arr[i] = (m == 0) ? 0 : k - m;
            }
            kArr[arr[i]]++;
        }

        // at position 0, we will have items % 7 = 0
        // it must be even
        if(kArr[0] % 2 != 0)    return false;

        for(int i = 1; i <= k / 2; i++) {
            if (kArr[i] != kArr[k - i])
                return false;
        }
        return true;
    }
}

// 1 2 3 14 15 16, k = 7

// 1 2 3 13
// 1 2 3 0 1 2
// 1 2 3 4 5 6

// make whole array as a[i] % k
// for each item find, if k - item exists in rest of array


// take care of neagtive nos
// let r = (abs(a[i]) % k);
// a[i] = (r != 0) ? k - r : 0


// 2 -51
// 2 5