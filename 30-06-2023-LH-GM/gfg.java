
class Solution {
    int isDivisible(String s) {
        // code here
        // refer - https://stackoverflow.com/questions/39385971/how-to-know-if-a-binary-number-divides-by-3#:~:text=Basically%20count%20the%20number%20of,2%20even%20non%2Dzero%20bits.
        int onesAtEvenPos = 0;
        int onesAtOddPos = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                if(i%2 == 0) {
                    onesAtEvenPos++;
                } else onesAtOddPos++;
            }
        }
        if(Math.abs(onesAtEvenPos - onesAtOddPos) % 3 == 0)
            return 1;
        return 0;
    }
}