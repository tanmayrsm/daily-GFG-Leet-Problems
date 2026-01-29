class Solution {
    public int[] findArray(int[] pref) {
        int[] prefCp = pref.clone();

        for(int i = 1; i < pref.length; i++)
            prefCp[i] = pref[i - 1] ^ pref[i];
    
        return prefCp;
    }
}

// how I came up with above approach -

// consider 

// pref = [5,2,0,3,1]

// Ans = [5,x,y,z]

// 5 ^ x = 2
// 2 ^ y = 0
// 0 ^ z = 3
// 3 ^ a = 1


// Question - 5 ^ x = 2
// 0101 ^       x    = 0010
//             0111

// 0010  ^    y      = 0000
//           0010

// 0000  ^    z      = 0011
//           0011

// 0011    ^  a     =  0001
//           0010


// main question -
// a ^ b = c, I know a & c, what is b?
// xor a both sides -
// a ^ a ^ b = a ^ c
// b = a ^ c 