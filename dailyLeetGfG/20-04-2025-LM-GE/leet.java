class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> mp = new HashMap<>();
        int total = 0;
        for(int ans : answers) {
            mp.put(ans, mp.getOrDefault(ans, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> p : mp.entrySet())
            total += Math.ceil((double)p.getValue() / (p.getKey() + 1)) * (p.getKey() + 1);

        return total;
    }
}