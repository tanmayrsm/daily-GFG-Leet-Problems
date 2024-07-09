class Solution {
    public double averageWaitingTime(int[][] customers) {
        double prev = -1, ans = 0;
        for(int i = 0; i < customers.length; i++) {
            if (customers[i][0] < prev)
                prev += customers[i][1];
            else
                prev = customers[i][0] + customers[i][1];
            ans += (prev -  customers[i][0]);
        }
        return ans / customers.length;
    }
}


