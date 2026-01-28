class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> mp = new LinkedHashMap<>();
        for (String a : arr) {
            mp.put(a, mp.getOrDefault(a, 0) + 1);
        }
        for(Map.Entry<String, Integer> e : mp.entrySet()) {
            if (k == 1 && e.getValue() == 1)    return e.getKey();
            if (e.getValue() == 1)  k--;
        }
        return "";
    }
}