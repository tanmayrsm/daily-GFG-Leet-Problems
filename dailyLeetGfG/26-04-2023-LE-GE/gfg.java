
class Solution {
    public static boolean is_possible_to_get_seats(int n, int m, int[] seats) {
        // code here
        if(n == 0)  return true;
        for(int i = 0; i < m; ) {
            int ct = 0;
            if(n <= 0)
                return true;
            boolean isLeft = i > 0 && seats[i-1] == 1;
            while(i < m && seats[i] == 0) {
                ct++;
                i++;
            }
            boolean isRight = i < m && seats[i] == 1;
            if(ct > 0) {
                if(isLeft)
                    ct--;
                if(isRight)
                    ct--;
            }
            if(ct == 0) i++;    // seat already occupied
            else if(ct > 0 && ct % 2 != 0)
                n -= (ct/2) + 1;
            else    n -= ct/2;
            
        }
        return n <= 0 ? true : false;
    }
}
        
