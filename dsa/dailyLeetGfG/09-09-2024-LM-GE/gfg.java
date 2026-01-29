class Solution {
    public static void sort012(ArrayList<Integer> a) {
        int n = a.size();
        int l = 0, r = n - 1, lz = n - 1, l2 = 0;

        while (l < n && r >= 0) {
            // Move the left pointer forward to skip over 0s
            while (l < n && a.get(l) == 0) l++;
            if (l < n && a.get(l) != 0 && lz > l) {
                // Move the lz pointer backward to find a 0
                while (lz > l && a.get(lz) != 0) lz--;
                if (lz > l && a.get(lz) == 0 && a.get(l) != 0) {
                    // Swap a[lz] with a[l]
                    int temp = a.get(lz);
                    a.set(lz, a.get(l));
                    a.set(l, temp);
                    lz--;
                }
            }
            l++;

            // Move the right pointer backward to skip over 2s
            while (r >= 0 && a.get(r) == 2) r--;
            if (r >= 0 && a.get(r) != 2 && l2 < r) {
                // Move the l2 pointer forward to find a 2
                while (l2 < r && a.get(l2) != 2) l2++;
                if (l2 < r && a.get(l2) == 2 && a.get(r) != 2) {
                    // Swap a[l2] with a[r]
                    int temp = a.get(l2);
                    a.set(l2, a.get(r));
                    a.set(r, temp);
                    l2++;
                }
            }
            r--;
        }
    }
}