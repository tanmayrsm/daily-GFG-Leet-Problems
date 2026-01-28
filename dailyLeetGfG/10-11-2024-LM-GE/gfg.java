
// User function Template for Java

// a,b : the arrays
class Solution {
    // Function to return a list containing the union of the two arrays.
    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        // add your code here
        Set<Integer> ans = new LinkedHashSet<>();
        int l = 0, r = 0;
        while(l < a.length && r < b.length) {
            if(a[l] < b[r])
                ans.add(a[l++]);
            else ans.add(b[r++]);
        }
        while(l < a.length)
            ans.add(a[l++]);
        while(r < b.length)
            ans.add(b[r++]);
        return new ArrayList<>(ans);
    }
}