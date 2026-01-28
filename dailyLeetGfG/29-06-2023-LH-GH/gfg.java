
class Solution{
    
    static int isHappy(int N) { 
        if (N == 1 || N == 7) 
            return 1; 
        int sum = N, curr = N; 
  
        // make the sum of square of digits equals a sngle digit num
        while(sum > 9) { // while noOfDigit in sum are >= 2 
            sum = 0;  // reset sum for every itr
  
            // this loop finds the sum of square of digits 
            while (curr > 0) { 
                int d = curr%10; // last digit 
                sum += d*d; 
                curr/=10; // Log10 comes from here in expected time omplexity
            } 
            
            if (sum == 1) // CONDITION - 1 satisfied 
                return 1;
            //else make the new number are curr numnber and proceed ahead.
            curr = sum; 
        } 
        if(sum == 7) // CONDITION - 2 - satisfied
            return 1; 
        return 0; 
    } 

    static int nextHappy(int N){    // referred soln
        // code here
        
        // condition-
        // a no can be happy, if either of below two are true -
        // 1 - if its sum is 1
        // 2 - if its single digit and is 1 OR 7 
        //   (Note if no becomes single digit, and is other than 1 OR 7, it can never be a happy no)
        
        int next=N+1;
        int res=next;
        // check for every num
        while(true)
        {
            if(isHappy(next)==1)
                return next;
            next++;
            res=next;
        }
    }
}