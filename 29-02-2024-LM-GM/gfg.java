
//User function Template for Java



class Solution {
    long sumBitDifferences(int[] arr, int n) {
        // code here
        long[] bits = new long[32];
        long ans = 0;
        // for each set bit for all nos at pos 'i' = m, no of unset bits at 'i', x = n - setBitsAt_i
        // ans += x * m * 2 (why 2 ? as x,y == y,x)
        // (1,2,5,9)
        // 0001
        // 0010
        // 0101
        // 1001
        // ----
        // 1 - 1 - 1 - 3 => set bits
        // 3 - 3 - 3 - 1 => unset bits
        
        // at pos 0 => 1 set bit will differ with 3 unset bits for other nos => i.e ans += 3
        // at pos 1 => same,
        // at pos 3 => same
        // at pos 4 => 3 set bits differ with 1 unset bit => ans += 3
        
        // finally as x,y == y,x , just double the answer, as reverse is same
        // ans *= 2
        
        
        
        for(int x : arr) {
            String s = Integer.toBinaryString(x);
            int m = s.length();
            int k = 31;
            for(int j = m - 1; j >= 0; j--) {
                if(s.charAt(j) == '1')
                    bits[k]++;
                k--;
            }
        }
        
        for(long x : bits) {
            ans += x * (n - x);
        }
        
        return ans * 2;  // (why 2 ? as x,y == y,x)
    }
}