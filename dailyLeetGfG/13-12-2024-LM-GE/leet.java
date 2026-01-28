
class Solution {
    public long findScore(int[] nums) {
        long ans = 0;
        // gt smallest elem
        // mark its index
        // 1 : [0, 9]       
        // 2 : [1, 3]
        // 
        // 
        // 
        // treemap
        // key : Queue

        TreeMap<Integer, Queue<Integer>> tm = new TreeMap<>();
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int no = nums[i];
            if (tm.containsKey(no)) {
                tm.get(no).offer(i);
            } else {
                tm.put(no, new LinkedList<>());
                tm.get(no).offer(i);
            }
        }

        while (!tm.isEmpty()) {
            int key = tm.firstKey();
            Queue<Integer> q = tm.get(key);
            while (!q.isEmpty()) {
                int firstIdx = q.poll();
                if (nums[firstIdx] >= 0) {
                    if (firstIdx - 1 >= 0 && nums[firstIdx - 1] >= 0) {
                        nums[firstIdx - 1] = 0 - nums[firstIdx - 1];
                    } 
                    if (firstIdx + 1 < nums.length && nums[firstIdx + 1] >= 0 ) {
                        nums[firstIdx + 1] = 0 - nums[firstIdx + 1];
                    }
                    ans += nums[firstIdx];
                    nums[firstIdx] = 0 - nums[firstIdx];
                }
            }
            tm.remove(key);
        }

        return ans;
    }
}