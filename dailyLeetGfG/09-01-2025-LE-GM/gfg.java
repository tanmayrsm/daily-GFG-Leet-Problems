class Solution {
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            if (i > 0)  
                arr[i] += arr[i - 1];
            else if (arr[i] == target) {
                ans.add(i + 1);
                ans.add(i + 1);
                return ans;
            }
            if (arr[i] >= target && mp.containsKey(arr[i] - target)) {
                ans.add(mp.get(arr[i] - target) + 2);
                ans.add(i + 1);
                return ans;
            }
            mp.put(arr[i], i);
        }
        ans.add(-1);
        return ans;
    }
}