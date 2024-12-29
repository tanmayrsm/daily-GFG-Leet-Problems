class Solution {
    public ArrayList<Integer> intersectionWithDuplicates(int[] a, int[] b) {
        // code here
        Arrays.sort(a);
        Arrays.sort(b);
        ArrayList<Integer> ans = new ArrayList<>();
        int l = 0, r = 0, n = a.length, m = b.length;
        while(l < n && r < m) {
            if (a[l] < b[r])
                while(l < n && a[l] < b[r])  l++;
            else
                while(r < m && a[l] > b[r])  r++;
            if (l < n && r < m && a[l] == b[r]) {
                int adder = a[l];
                ans.add(a[l]);
                while(l < n && a[l] == adder)   l++;
                while(r < m && b[r] == adder)   r++;
            }
        }
        return ans;
    }
}