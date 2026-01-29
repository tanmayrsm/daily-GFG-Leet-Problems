class Solution {
    // Function to find the majority elements in the array
    public List<Integer> findMajority(int[] nums) {
        // Your code goes here.
        List<Integer> list = new ArrayList<>();
           HashMap<Integer,Integer> map = new HashMap<>();
           HashSet<Integer> set= new HashSet<>();
           int n= nums.length;
           int m = n/3;
           for(int i=0;i<n;i++)
           {
               map.put(nums[i],map.getOrDefault(nums[i],0)+1);
               set.add(nums[i]);
           }
           for(Integer i: set)
           {
               if(map.containsKey(i) && map.get(i)>m)
               {
                   list.add(i);
               }
           }
           return list;
    }
}