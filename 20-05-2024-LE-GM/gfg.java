
class Solution  //reff
{
    public long PowMod(long x, long n, long m)
    {//exponentiation by squaring method -
        if(n == 0) return (1 % m);
        if(n == 2) return (x * x)% m;
        long ans = 1l;//stores the ans
        long sq = x;//helps to update the ans
        while(n > 0){
            if(n % 2 == 1){//if our power is odd, we update the ans by multiplying with sq
                ans = (ans * sq)% m;
            }
            sq = (sq * sq) % m;//we keep on updating the sq
            n = n / 2;//and dividing by 2
        }
        return ans % m;
    }
}

// 2  6
// 4  3
// 16 1
// 265 0


// 2 -> 2^2 -> 2^4 -> 2^6