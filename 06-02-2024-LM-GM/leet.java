class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> mapper = new HashMap<>();
        
        for(String s : strs) {
            char[] characters = s.toCharArray();
            Arrays.sort(characters);
            String sorted = new String(characters);
            
            if(mapper.get(sorted) == null){
                mapper.put(sorted, new ArrayList<>());
            }
            mapper.get(sorted).add(s);
        }
        
        ans.addAll(mapper.values());
        return ans;
    }
}