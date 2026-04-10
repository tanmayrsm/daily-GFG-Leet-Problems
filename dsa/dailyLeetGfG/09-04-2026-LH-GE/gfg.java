class Solution {
    ArrayList<Integer> intersection(int[] a, int[] b) {
        // code here
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = a.length, m = b.length, i = 0, j = 0;
        while(i < n && j < m) {
            while(i < n && a[i] < b[j])  i++;
            while(i < n && j < m && a[i] > b[j])  j++;
            if(i < n && j < m && a[i] == b[j]) {
                int curr = a[i];
                ans.add(a[i]);
                while(i < n && a[i] == curr)    i++;
                while(j < m && b[j] == curr)    j++;
            }
        }
        return ans;
    }
}