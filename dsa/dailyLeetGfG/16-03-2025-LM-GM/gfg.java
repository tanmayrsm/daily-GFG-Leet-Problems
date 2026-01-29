class Solution {
    static int minJumps(int[] arr) {
        // your code here
        int jumps = 0, l = 0, r = 0, R = 0, L = 0;
        while(R < arr.length - 1) {
            l = Integer.MAX_VALUE;
            r = Integer.MIN_VALUE;
            for (int i = L; i <= R; i++) {
                int next = arr[i] + i;
                if(next > R) {
                    l = R + 1;
                    r = Math.max(r, next);
                }
            }
            if (l == Integer.MAX_VALUE) return -1;
            // System.out.println(l + "::" + r);
            R = r;
            L = l;
            jumps++;
        }
        return jumps;
    }
}