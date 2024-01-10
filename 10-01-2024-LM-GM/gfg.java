class Solution{
    int longSubarrWthSumDivByK(int a[], int n, int k)
    {
        // Complete the function
        HashMap<Integer,Integer> mp= new HashMap<>();
        int mx=0,r=0,s=0;
        mp.put(0,-1);
        for(int i=0;i<n;i++){
            s+=a[i];
            r=s%k;
            if(r<0)r+=k;    // mod of negative values is not positive, hence add k in
            
            if(mp.containsKey(r)){
                mx=Math.max(mx,i-mp.get(r));
            } else mp.put(r,i);
        }
        return mx;
    }
 
}
