
class Solution{
    static int setSetBit(int x, int y, int l, int r){
        // code here
        StringBuilder aBin = new StringBuilder(Integer.toBinaryString(x)).reverse();
        StringBuilder bBin = new StringBuilder(Integer.toBinaryString(y)).reverse();
        StringBuilder res = new StringBuilder(aBin.toString());
        
        if(aBin.length() < bBin.length()) {
            int appendNoOfZeroes = bBin.length() - aBin.length();
            while(appendNoOfZeroes > 0) {
                res.append("0");
                appendNoOfZeroes--;
            }
        }
        
        for(int i = l - 1; i < r; i++) {
            if(i >= bBin.length())
                break;
            if(bBin.charAt(i) == '1')
                res.setCharAt(i, '1');
        }
        
        return Integer.parseInt(res.reverse().toString(), 2);
    }
}