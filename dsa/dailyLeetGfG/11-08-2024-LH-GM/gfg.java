class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        // Your code here
        Arrays.sort(arr, (j1, j2) -> Integer.compare(j1.profit, j2.profit));
        int currProfit = 0, ct = 0;
        boolean[] slotBooked = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            int deadline = arr[i].deadline - 1;
            while(deadline >= 0 && !slotBooked[deadline])
                deadline--;
            if (deadline >= 0) {
                currProfit += arr[i].profit;
                ct++;
                slotBooked[deadline] = true;
            }
        }
        return new int[] {ct, currProfit};
    }
}

/*
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
*/