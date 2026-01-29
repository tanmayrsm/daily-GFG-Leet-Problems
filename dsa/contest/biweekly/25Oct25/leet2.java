class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(9 * num < sum)
            return "";
        // if(sum / num > num || (sum / num == num && sum % num > 0)) return "";
        char[] arr = new char[num];
        Arrays.fill(arr, '0');
        boolean res = solve(num - 1, sum, arr);
        if(!res)    return "";
        StringBuilder sb = new StringBuilder(new String(arr));
        // System.out.println("sb ::" + sb.toString());
        return sb.reverse().toString();
    }
    private boolean solve(int n, int rem, char[] arr) {
        // System.out.println("n ::" + n + "::" + rem);
        if(rem == 0) {
            // arr[n] = (char)(rem + '0');
            return true;
        }
        if((n == -1 && rem > 0) || n < 0)
            return false;
        for(char x = '9'; x >= '0'; x--) {
            int val = (int)(x - '0');
            // System.out.println("try ::" + val);
            arr[n] = x;
            if(rem - val >= 0 && solve(n - 1, rem - val, arr))
                return true;
        }
        return false;
    }
}