
class Solution{
    static boolean[] arr;
    static long largestPrimeFactor(int N) {
        // code here
        // try dividing completely by all prime nos - 2,3,5,7,11,13,17
        long ans = -1;
        int i = 2;
        while(i * i <= N) {
            while(N % i == 0) {
                ans = i;
                N = N / i;
            }
            i++;
        }
        if(N > 1)   // N is not divisible further by any prime no, i.e N itself is prime no now
            return N;
        return ans;
    }
    
}