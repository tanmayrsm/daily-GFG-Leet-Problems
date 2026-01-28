class Solution {
    public int missingNumber(int[] arr) {
        // code here
        Arrays.sort(arr);
        int n = arr.length, posi = 1;
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && arr[i] == arr[i - 1]) continue;
            if (arr[i] > 0) {
                // System.out.println(posi + "::" + arr[i]);
                if (arr[i] > posi)  return posi;
                else posi++;
            }
        }
        return posi;
    }
}