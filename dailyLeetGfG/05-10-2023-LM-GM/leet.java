class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> store = new HashMap<>();
        int ct = nums.length / 3;
        for(int x : nums) {
            int freq = store.getOrDefault(x, 0);
            store.put(x, freq + 1);
        }
        for(Map.Entry<Integer, Integer> x : store.entrySet()) {
            if(x.getValue() > ct)
                ans.add(x.getKey());
        }
        return ans;

    }
}