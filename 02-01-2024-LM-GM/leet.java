class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, Integer> mp = new LinkedHashMap<>();
        for(int x : nums) 
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        
        while(true) {
            List<Integer> item = new ArrayList<>();
            boolean done = true;
            for(Map.Entry<Integer, Integer> x :  mp.entrySet()) {
                if(x.getValue() > 0) {
                    done = false;
                    item.add(x.getKey());
                    mp.put(x.getKey(), x.getValue() - 1);
                }
            }
            if(!done)   ans.add(item);
            if(done)    break;
        }

        return ans;
    }
}