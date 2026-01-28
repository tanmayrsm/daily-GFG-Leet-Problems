
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> ct = new HashMap<>();
        String[] s1_ = s1.split(" "), s2_ = s2.split(" ");

        for(String s : s1_)
            ct.put(s, ct.getOrDefault(s, 0) + 1);
        for(String s : s2_)
            ct.put(s, ct.getOrDefault(s, 0) + 1);
        
        for(Map.Entry<String, Integer> e : ct.entrySet())
            if(e.getValue() == 1)
                ans.add(e.getKey());

        return ans.toArray(new String[0]);
    }
}