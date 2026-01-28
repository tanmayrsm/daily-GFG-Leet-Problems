class Solution {    // reff
    public boolean primeSubOperation(int[] nums) {
        int n=nums.length-2;
        for(int i=n;i>=0;i--)
        {
            if(nums[i]>=nums[i+1])
            {
                int diff=nums[i]-nums[i+1]+1;
                
                while(diff<nums[i])
                {
                    if(isprime(diff))
                    {
                        nums[i]-=diff;
                        break;
                    }
                    diff++;
                }
                if(nums[i]>=nums[i+1])
                 return false;
            }
        }
        return true;
    }

    boolean isprime(int val)
    {
        if(val==1 || val==0)
            return false;
        for(int i=2;i<=val/2;i++)
        {
            if(val%i==0)
                return false;
        }
        return true;
    }
}