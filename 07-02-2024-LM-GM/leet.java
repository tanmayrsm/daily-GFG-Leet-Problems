class Solution {
    // function to sort hashmap by values
    private static Map<Character, Integer>
    sortByValue(Map<Character, Integer> hm)
    {
        Map<Character, Integer> temp
            = hm.entrySet()
                  .stream()
                  .sorted((i1, i2)
                              -> i2.getValue().compareTo(
                                  i1.getValue()))
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1, LinkedHashMap::new));
 
        return temp;
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.get(c) != null) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c,1);
            }
        }
        map = sortByValue(map);
        String ans = "";
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            for(int i = 0; i < entry.getValue(); i++)
                ans += entry.getKey();
        }
        return ans;

    }
}