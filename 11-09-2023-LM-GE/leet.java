class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<List<Integer>>> store = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < groupSizes.length; i++) {
            int x = groupSizes[i];

            if(store.containsKey(x)) {
                int n = store.get(x).size();
                List<Integer> val = store.get(x).get(n - 1);    // get last list elem
                if(val.size() < x) {
                    val.add(i);
                }   else {
                    List<Integer> newer = new ArrayList<>();
                    newer.add(i);
                    store.get(x).add(newer);
                }
            } else {
                store.put(x, new ArrayList<>());
                List<Integer> newer = new ArrayList<>();
                newer.add(i);
                store.get(x).add(newer);
            }
        }

        for(Map.Entry<Integer, List<List<Integer>>> em : store.entrySet()) {
            ans.addAll(em.getValue());
        }

        return ans;

    }
}