
class Solution {
    static boolean lemonadeChange(int N, int bills[]) {
        // code here
        
        int no5, no10, no15;
        no5 = no10 = no15 = 0;
        for(int x : bills) {
            if(x == 5) {
                no5++;
            } else if (x == 10) {
                if(no5 == 0)    return false;
                no5--;
                no10++;
            }  else if (x == 20) {  
                // greedily, I'll keep max no5 with me, hence 
                // if customer gives 20$, I'll give him 10$ + 5$ as my first preference 
                // if I dont have sufficient 10$, I'll give him 5$ + 5$ + 5$
                
                if(no10 >= 1 && no5 >= 1) {
                    no5--;
                    no10--;
                } else if(no5 >= 3) {
                    no5 -= 3;
                } else
                    return false;
            }
        }
        return true;
    }
}