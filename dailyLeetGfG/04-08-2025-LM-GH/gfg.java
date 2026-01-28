class Solution {
    public int maxRectSum(int mat[][]) {
        // code here
        int n = mat.length, m = mat[0].length, ans = Integer.MIN_VALUE;

        // iterate column wise in  brute force fashion
        for (int j = 0; j < m; j++) {   // m
            int[] temp = new int[n];
            for (int i = j; i < m; i++) {   // m
                for (int k = 0; k < n; k++) {   // n
                    temp[k] += mat[k][i];
                }
                ans = Math.max(ans, kadane(temp)); // n
            }
        }

        return ans;
        // TC -> O(m * m * (n + n))
    }

    private int kadane(int[] arr){
        int maxo = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            maxo = Math.max(maxo, sum);
            if (sum < 0)
                sum = 0;
        }
        return maxo;
    }
};