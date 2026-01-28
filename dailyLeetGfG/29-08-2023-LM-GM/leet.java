class Solution {
    public int bestClosingTime(String customers) {
        // it means at ith hour, I need to find
        // no of 'N' at LHS
        // no of 'Y' at curr pos and RHS
        // to calculate penalty at hour 'i'
        int n = customers.length();
        int[] noOfYes = new int[n];
        int ct = 0, ans = Integer.MAX_VALUE, hour = 0;

        for(int i = 0; i < n; i++)
            if(customers.charAt(i) == 'Y')
                noOfYes[i] = ++ct;
            else noOfYes[i] = ct;
        
        for(int i = 0; i < n + 1; i++) {
            if(i == 0) {
                ans = Math.min(ans,  noOfYes[n - 1]);   // no of yes on right
            } else if (i == n) {
                if(n - noOfYes[i - 1] < ans)
                    hour = n;
                ans = Math.min(ans, n - noOfYes[i - 1]);    // no of 'N' on left
            } else {
                char c = customers.charAt(i);
                int yesOnRight = noOfYes[n - 1] - noOfYes[i - 1];   // last yes count - yes till (i-1) count => i to n ka yes count
                int noOnLeft = Math.abs(noOfYes[i - 1] - i);
                if(yesOnRight + noOnLeft < ans)
                    hour = i;
                ans = Math.min(ans, yesOnRight + noOnLeft);
               }
        }
        
        return hour;
    }
}