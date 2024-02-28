
//User function Template for Java

class Solution{
    int DivisibleByEight(String s){
        //code here
        // 2, 12 -> not divisible
        // 112 -> divisible

        // 132 -> not divisible
        // 32 -> divisible

        // 1132
        // 11%8 => 3
        // 332 => 33 % 8 =>1
        // 12 => 12 % 8 => 4

        // algo - actual divisible algo 

        int n = s.length();
        int lastDig = (int)(s.charAt(n - 1) - '0');
        if(lastDig % 2 != 0)    return -1;
        if(n == 1)  
            return (lastDig % 8 == 0) ? 1 : -1;
        
        int currNo = (int)(s.charAt(0) - '0');
        for(int i = 1; i < n; i++) {
            // get current first two digits
            currNo =  currNo * 10 + (int)(s.charAt(i) - '0');
            // System.out.println("new currno ::" + currNo);
            int newNo = currNo % 8;
            if(newNo == 0)
                currNo = 0;   // if no divisible by 8, make it 0, and try dividing next characters
            else currNo = newNo;
        }
        return currNo == 0 ? 1 : -1;

    }
}