
class Solution {
    public static int getMaximum(int N, int[] arr) {
        // code here
        long sum = 0;
        for(int x : arr) {
            sum += x;
        }
        for(int i = N; i > 1; i--) {
            if(sum % i == 0)
                return i;
        }
        return 1;
    }
}
        
