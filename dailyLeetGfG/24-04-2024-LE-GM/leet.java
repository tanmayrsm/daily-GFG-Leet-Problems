class Solution {
    public int tribonacci(int n) {
        int t0 = 0, t1 = 1, t2 = 1;
        if(n == 0)  return 0;
        if(n <= 2)  return 1;
        for(int i = 3; i <= n; i++) {
            int temp1 = t1, temp2 = t2;
            t2 = t0 + t2 + t1;
            t1 = temp2;
            t0 = temp1;
        }
        return t2;
    }
}