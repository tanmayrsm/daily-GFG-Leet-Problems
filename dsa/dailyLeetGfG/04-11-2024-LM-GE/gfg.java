class Solution {
    public List<List<Integer>> findTriplets(int[] arr) {
        // Your code here
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            Map<Integer, List<Integer>> mp = new HashMap<>();
            for(int j = i + 1; j < arr.length; j++) {
                int cSum = arr[i] + arr[j];
                if(mp.containsKey(0 - cSum)) {
                    for(Integer idx : mp.get(0 - cSum)) {
                        List<Integer> k = Arrays.asList(i, j, idx);
                        Collections.sort(k);
                        ans.add(k);
                    }
                } 
                if(!mp.containsKey(arr[j])) 
                    mp.put(arr[j], new ArrayList<>());
                mp.get(arr[j]).add(j);
            }
        }
        return ans;
    }
}