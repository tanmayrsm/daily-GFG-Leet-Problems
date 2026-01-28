class Solution {
    public List<List<Integer>> findTriplets(int[] arr) {
        // Your code here
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> mp = new HashMap<>();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                // x + y + z = 0
                // z = - (x + y)
                if (mp.containsKey(0 - (arr[i] + arr[j]))) {
                    for (Integer x :  mp.get(0 - (arr[i] + arr[j]))) {
                        List<Integer> p = new ArrayList<>();
                        if (x > i && x > j) {
                            p.add(i);
                            p.add(j);
                            p.add(x);
                            ans.add(p);
                        }
                    }
                }
            }
            if(!mp.containsKey(arr[i]))
                mp.put(arr[i], new ArrayList<>());
            mp.get(arr[i]).add(i);
        }
        
        return ans;
        
    }
}