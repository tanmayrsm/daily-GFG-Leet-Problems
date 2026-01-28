class Solution {
    public int minCandy(int arr[]) {
        // code here
        int n = arr.length;
        if(n == 1)  return 1;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if (arr[i] > arr[i + 1])
                    res[i] = res[i + 1] + 1;
            }
            else if (i == n - 1) {
                if (arr[i] > arr[i - 1])
                    res[i] = res[i - 1] + 1;
            }
            else {
                if(arr[i] > arr[i - 1]) res[i] = res[i - 1] + 1;
                else if (arr[i] > arr[i + 1]) res[i] = res[i + 1] + 1;
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            if(i == 0) {
                if (arr[i] > arr[i + 1] && res[i] <= res[i + 1])
                    res[i] = res[i + 1] + 1;
            }
            else if (i == n - 1) {
                if (arr[i] > arr[i - 1] && res[i] <= res[i - 1])
                    res[i] = res[i - 1] + 1;
            }
            else {
                if(arr[i] > arr[i - 1] && res[i] <= res[i - 1]) res[i] = res[i - 1] + 1;
                if (arr[i] > arr[i + 1] && res[i] <= res[i + 1]) res[i] = res[i + 1] + 1;
            }
        }
        return Arrays.stream(res).sum();
    }
}
