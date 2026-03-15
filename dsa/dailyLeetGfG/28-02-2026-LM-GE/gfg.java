class Solution {
    public static ArrayList<Integer> findClosestPair(int arr1[], int arr2[], int x) {
        // code here
        int n = arr1.length, m = arr2.length;
        int a = arr1[0], b = arr2[m - 1], maxo = Integer.MAX_VALUE;
        int l = 0, r = m - 1;
        while(l < n && r >= 0) {
            int sum = arr1[l] + arr2[r];
            if(Math.abs(x - sum) < maxo) {
                maxo = Math.abs(x - sum);
                a = arr1[l];
                b = arr2[r];
            }
            if(arr1[l] + arr2[r] <= x) {
                l++;
            } else r--;

        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(a); ans.add(b);
        return ans;
    }
}

// 10 20 30 40
// 1 4 5 7
// x=25


