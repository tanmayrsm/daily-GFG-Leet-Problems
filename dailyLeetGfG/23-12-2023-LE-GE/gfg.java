
class Solution 
{
    //Function to find all elements in array that appear more than n/k times.
    public int countOccurence(int[] arr, int n, int k) 
    {
        // your code here,return the answer
        int ct = n / k, ans = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for(int x :  arr)
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        for(Map.Entry<Integer, Integer> entry : mp.entrySet())
            if(entry.getValue() > ct)  
                ans++;
        
        return ans;
    }
}
