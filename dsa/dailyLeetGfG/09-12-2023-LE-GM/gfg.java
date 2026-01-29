
class Solution {
    static int smithNum(int n) {
        // code here
        if(n == 1) {
            return 0;
        }
        int sumOfNos = 0, sumOfPrimes = 0;
        sumOfNos = sumOfDigits(n);
        int ct = 2, primeNoFactorCount = 0;
        while(n > 1) {
            while(n % ct == 0) {
                sumOfPrimes += sumOfDigits(ct);
                n /= ct;
                primeNoFactorCount++;
            }
            System.out.println("SOP ::" + sumOfPrimes + "::" + ct + "::" + n);
            ct++;
            if(sumOfPrimes > sumOfNos)  return 0;
        }
        if(primeNoFactorCount == 1) return 0;   // as Smith no is always composite (not prime)
        return sumOfNos == sumOfPrimes ? 1 : 0;
    }

    private static int sumOfDigits(int nCopy) {
        int sumOfNos = 0;
        while(nCopy > 0) {
            sumOfNos += nCopy % 10;
            nCopy /= 10;
        }
        return sumOfNos;
    }
};