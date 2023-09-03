
class Solution 
{
    //Function to find length of longest increasing subsequence.
    static int longestSubsequence(int n, int a[])
    {
        // referred soln
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int ans=0;
        for(int i:a){
            Integer ceil = map.ceilingKey(i);
            if(ceil==null) map.put(i, map.size()+1 );
            // if(ceil==i)    //eat 5 star do nothing
            else if(i<ceil){
                //bcz merko ceil ki value chote se chote rkne h
                //ceil choti kr de
                map.put(i,map.get(ceil));
                map.remove(ceil);
            }
            // System.out.println(ceil+" "+map);
            ans=Math.max(map.get(i),ans);
        }
        return ans;
    }
} 