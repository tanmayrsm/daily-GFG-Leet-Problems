
class Solution {
    public static boolean isFrequencyUnique(int n, int[] arr) {
        // code here
        Map<Integer, Integer> map = new HashMap<>();
        for(int x : arr) {
            if(map.get(x) != null) {
                map.put(x, map.get(x) + 1);   
            }
            else    map.put(x, 1);
        }
        Set<Integer> s = new HashSet<>(map.values());
        return s.size() == map.values().size();
    }
}
        
