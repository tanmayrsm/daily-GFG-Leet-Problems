class Solution {
    public int tupleSameProduct(int[] nums) {
        //Arrays.sort(nums);
        int n = nums.length;
        int ct = 0;

        HashMap<Integer , Integer>map = new HashMap<Integer,Integer>();

        for(int  i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                map.put(nums[i]*nums[j] , map.getOrDefault(nums[i]*nums[j] , 0)+1);
            }
        }

        //System.out.println(map);
        for(int a : map.values()){
            if(a>=2){
                ct += ((a-1)*(a))*4;//(8/2 == 4)
            }
        }

        return ct;

    }
}