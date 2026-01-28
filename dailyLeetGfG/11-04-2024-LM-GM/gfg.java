
class Solution{
    
    // function to convert a given Gray equivalent n to Binary equivalent.
    public static int grayToBinary(int n) {
        
        // Your code here
        String gray = Integer.toBinaryString(n);
        int N = gray.length();
        StringBuilder bin = new StringBuilder(gray.charAt(0) + "");
        char prev = bin.charAt(0);
        for(int i = 1; i < N; i++) {
            char grayC = gray.charAt(i);
            char next = '@';
            if(grayC == '1')
                next = (prev == '0') ? '1' : '0';
            else next = prev;
            
            bin.append(next + "");
            prev = next;
        }
        return Integer.parseInt(bin.toString(), 2);
        
    }
       
}
