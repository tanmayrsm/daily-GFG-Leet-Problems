class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        int maxBinLength = "0111011100110101100101000000000".length();  // binary of 10^9
        char[] A = computeBinary(maxBinLength, a);
        char[] B = computeBinary(maxBinLength, b);
        char[] C = computeBinary(maxBinLength, c);

        for(int i = maxBinLength - 1; i >= 0; i--) {
            
            if(C[i] == '0' && (A[i] != '0' || B[i] != '0')) {   
                // if C is '0' and either or both of A and B are '1', flip both or one of them accordingly
                ans += A[i] == '1' && B[i] == '1' ? 2 : 1;
            }
            else if (C[i] == '1' && (A[i] == '0' && B[i] == '0'))
                // C is '1' and both A, B are '0', flip only one of them
                ans++;
        }

        return ans;
    }

    private static char[] computeBinary(int length, int a) {
        char[] x = new char[length];
        Arrays.fill(x, '0');

        String binaryNo = Integer.toBinaryString(a);
        int ptr = length - 1;

        for(int i = binaryNo.length() - 1; i >= 0; i--) {
            x[ptr--] = binaryNo.charAt(i);
        }

        return x;
    }
}