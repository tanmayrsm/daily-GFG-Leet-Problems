class Solution {
    public static ArrayList<Integer> countLessEq(int a[], int b[]) {
        // code here
        Arrays.sort(b);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            int idx = giveLessThanEqualToCount(b, a[i]);
            ans.add(idx + 1);
        }
        return ans;
    }
    private static int giveLessThanEqualToCount(int[] b, int x) {
        int n = b.length, l = 0, r = n - 1, idx = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (b[mid] > x) r = mid - 1;
            else {
                idx = mid;
                l = mid + 1;
            }
        }
        return idx;
    }
}