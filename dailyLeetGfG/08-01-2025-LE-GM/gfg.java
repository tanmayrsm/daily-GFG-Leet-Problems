class Solution {
    // Function to count the number of possible triangles.
    static int countTriangles(int arr[]) {
        // code here
        int ans = 0, n = arr.length;
        Arrays.sort(arr);
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while(l < r) {
                while(l < r && arr[l] + arr[r] <= arr[i])  l++;
                if (l < r && arr[l] + arr[r] > arr[i]) {
                    ans += r - l;
                    r--;
                } else break;
            }
            // System.out.println(i + "::" + ans);
        }
        return ans;
    }
}