class Solution {
    public int kthElement(int a[], int b[], int k) {
        // code here
        int n = a.length, m = b.length;
        int i = 0, j = 0, currK = 0;
        while(i < n && j < m) {
            if (a[i] <= b[j]) {
                i++;
                currK++;
                if (currK == k) return a[i - 1];
            } else {
                j++;
                currK++;
                if (currK == k) return b[j - 1];
            }
        }
        while(i < n) {
            i++;
            currK++;
            if (currK == k) return a[i - 1];
        }
        while(j < m) {
            j++;
            currK++;
            if (currK == k) return b[j - 1];
        }
        return 0;
    }
}