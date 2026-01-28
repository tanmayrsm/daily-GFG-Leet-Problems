// User function Template for Java

class Solution {
    public int kthElement(int a[], int b[], int k) {
        // code here
        int curr = 0, l = 0, r = 0;
        while(l < a.length && r < b.length) {
            if (a[l] <= b[r]) {
                if (curr + 1 == k)
                    return a[l];
                l++;
            } else {
                if (curr + 1 == k)
                    return b[r];
                r++;
            }
            curr++;
        }
        while(l < a.length) {
            if (curr + 1 == k)
                    return a[l];
                l++;
            curr++;
        }
        while(r < b.length) {
            if (curr + 1 == k)
                    return b[r];
                r++;
            curr++;
        }
        return -1;
    }
}