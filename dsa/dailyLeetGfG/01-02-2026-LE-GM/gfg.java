class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
        TreeMap<Integer, Integer> ts = new TreeMap<>((Integer a, Integer b) -> Integer.compare(b, a));
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++)
            ts.put(arr[i], ts.getOrDefault(arr[i], 0) + 1);
        ans.add(ts.firstKey());

        for(int i = k; i < arr.length; i++) {
            int tail = arr[i - k], next = arr[i];
            if(ts.get(tail) == 1)   ts.remove(tail);
            else ts.put(tail, ts.get(tail) - 1);
            ts.put(next, ts.getOrDefault(next, 0) + 1);
            ans.add(ts.firstKey());
        }
        return ans;
    }
}