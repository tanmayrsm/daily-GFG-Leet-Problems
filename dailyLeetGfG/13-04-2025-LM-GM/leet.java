class Solution {
    public int countGoodNumbers(long n) {
        double ans = 1;
        int mod = 1000000007;
        if(n == 1)  return 5;
        ans = power(5 , (n / 2 + (n % 2 == 1 ? 1 : 0))) % mod * power(4 , n / 2) % mod;
        return (int)(ans % mod);
    }

    public long power(int a, long p){   // reff
        int m = 1000000007;
        if(p == 0) return 1;
        long temp = power(a, p/2);

        if(p%2 == 1){
            return ( a * temp%m * temp%m )%m; 
        }else{
            return ( temp%m * temp%m )%m;
        }
    }
}

// at any even index, nos possible are
// 0,2,4,6,8
// at odd pos
// 2,3,5,7

// n = 4
// 5 * 4 * 5 * 4