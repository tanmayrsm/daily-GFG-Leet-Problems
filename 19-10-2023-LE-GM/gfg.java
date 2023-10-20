
class Solution
{
    //Function to find a continuous sub-array which adds up to a given number.
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) 
    {
        // Your code here
        ArrayList<Integer> ans = new ArrayList<>();
        int sum = 0, k = 0;
        if(s == 0) {
            ans.add(-1);
            return ans;
        }
        
        for(int i=0;i<n;i++)
        {
            sum+=arr[i];
            while(sum>s)
            {
                sum-=arr[k];k++;
            }
            if(sum==s){
                ans.add(k+1); ans.add(i+1);
                return ans;
            };
        }
        ans.add(-1);
        return ans;
    }
}