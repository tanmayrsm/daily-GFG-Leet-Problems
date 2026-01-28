
class CheckBit
{
    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k)
    {
        // Your code here
        
        // saw video hint
        long newer = n & (long)Math.pow(2, k);
        return newer != 0;
    }
    
}
