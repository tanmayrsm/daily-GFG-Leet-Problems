class Solution {
    public int totalElements(int[] arr) {
        // code here
        // code here
        int left = 0;
        int length = 0;
        int max = 0;


        HashMap<Integer, Integer > hm= new HashMap<>();

        for (int right = 0; right < arr.length; right++) {
            hm.put(arr[right], hm.getOrDefault(arr[right], 0) + 1);

            while (hm.size() > 2) {
                hm.put(arr[left], hm.get(arr[left]) - 1);
                if (hm.get(arr[left]) == 0) {
                    hm.remove(arr[left]);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}