
//User function Template for Java
class Solution {
    static String armstrongNumber(int n){
        // code here
        int total = 0, N = n;
        while(n > 0) {
            total += Math.pow(n % 10, 3);
            n /= 10;
        }
        return total == N ? "Yes" : "No";
    }
}