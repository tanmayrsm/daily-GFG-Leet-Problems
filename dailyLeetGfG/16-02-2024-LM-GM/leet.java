class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int ct = 0, n = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for(int x : arr) {
            if(!mp.containsKey(x))  mp.put(x, 0);
            mp.put(x, mp.get(x) + 1);
        }
        List<Integer> freq = new ArrayList<>(mp.values());
        n = freq.size();
        Collections.sort(freq);
        for(int x : freq) {
            if(x <= k) {
                ct++;
                k -= x;
            }
        }
        return n - ct;
    }
}