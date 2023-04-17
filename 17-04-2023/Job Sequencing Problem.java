class SortbyProfit implements Comparator<Job> {
    public int compare(Job a, Job b)
    {
        return a.profit - b.profit;
    }
}

class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        // Your code here
        Arrays.sort(arr, new SortbyProfit());
        int res=0; int tot_job=0;
        boolean[] slot = new boolean[n]; // track of free time slots. - all false now

       //itr for all jobs.
        for (int i=0; i<n; i++)
        {
            //linear search [deadline to 0] - to check if any slot is any time frame slot is free below deadline
            for (int j= arr[i].deadline-1; j>=0; j--) 
            { 
                //if free slot found - add the profits, tot_job++, mark slot filled 
                if (slot[j]==false)
                { 
                    res += arr[i].profit;  tot_job++;
			        slot[j] = true;
			        break; 
		        } 
	        } 
	    } 

	   int ans[] = {tot_job, res};
	    return ans;
    }
}