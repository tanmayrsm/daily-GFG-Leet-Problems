// User function Template for Java
class Solution {
    public int countSubarrays(int arr[], int k) {
        // code here
        Map<Integer,Integer> map=new HashMap<>();
        int curSum=0;
        int ans=0;
        
        for(int ele :arr){
            curSum+=ele;    
            if(curSum == k)
                ans++;
            int remain=curSum-k;
            
            if(map.containsKey(remain))
                ans+=(map.get(remain));
            
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
     return ans;   
    }
}