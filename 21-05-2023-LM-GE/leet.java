class Solution {
    class Pair {
        int x;
        int y;
        Pair(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
    class topK implements Comparator<Pair> {
        public int compare(Pair s1, Pair s2) {
                if (s1.y < s2.y)
                    return 1;
                else if (s1.y > s2.y)
                    return -1;
                                return 0;
                }
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[k];
        int ct = 0;
        int f = 0;
        for(int x : nums) {
            if(map.get(x) == null) {
                map.put(x, 1);
            }
            else {
                map.put(x, map.get(x) + 1);
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new topK());
        for(Map.Entry<Integer, Integer> x : map.entrySet()) {
            pq.offer(new Pair(x.getKey(), x.getValue()));
        }
        while(!pq.isEmpty() && ct < k) {
            Pair x = pq.poll();
            ans[ct++] = x.x;
        }
        return ans;
    }
}