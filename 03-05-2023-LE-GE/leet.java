class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        
        Set<Integer> n1 = new HashSet<>();
        Set<Integer> n2 = new HashSet<>();
        for(int x : nums1)
            n1.add(x);
        for(int x : nums2)
            n2.add(x);
        for(int x : n1)
            if(!n2.contains(x))
                ans.get(0).add(x);
        for(int x : n2)
            if(!n1.contains(x))
                ans.get(1).add(x);

        return ans;

    }
}