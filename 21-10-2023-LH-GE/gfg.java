
//User function Template for Java
class Solution{
    static long sumOfDivisors(int n){
        // code here
        long sum=0;
        for(int i=1;i<=n;i++)
        {
            long x = (n / i) * i;
            // System.out.println("i :: " + i + " :: " + x);
            sum+= (n/i)*i;
        }
        return sum;
    }
}