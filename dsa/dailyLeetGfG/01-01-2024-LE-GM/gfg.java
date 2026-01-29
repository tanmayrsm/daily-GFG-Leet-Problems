
class Solution {    // reff soln
    public boolean canPair(int[] nums, int k) {
        // Code here
        int n=nums.length;
        if(n%2==1)
        return false;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            nums[i]=nums[i]%k;
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        
        for(int i=0; i<n; i++){
            int a=nums[i];
            int b=k-nums[i];
            if(a!=0){
                //System.out.println(map.get(a)+" "+map.get(b));
                if(map.containsKey(a)==map.containsKey(b) && map.get(a)==map.get(b)){
                    map.remove(a);
                    map.remove(b);
                }
                else
                return false;
            }
        }
        return true;
    }
}