class Solution {
    class Pair {
        int index;
        int value;
        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public long maxTotalValue(int[] nums, int k) {
        List<Pair> arr = new ArrayList<>();
        int n = nums.length, l = 0, r = n - 1;
        for (int i = 0; i < n; i++) {
            arr.add(new Pair(i, nums[i]));
        }
        Collections.sort(arr, ((Pair a, Pair b) -> Integer.compare(a.value, b.value)));
        while(l < r) {
            int subArraysBetweenl&r = getSubarray(arr.get(l).index, arr.get())
        }
    }
}

/*

from min, max how many subsets can u achieve ?
_ min _ _ _ max _ _

until k > 0
1 5 ... 80 90

90 - 1 -> 89
80 - 1 / 90 - 5 ? 79 / 85 -> 85, then pop 1

5 ... 80 90
find all subarrays for 5, 90 wala index

List<Pair> -> sorted by value
Pair {
    index
    value
}

*/


