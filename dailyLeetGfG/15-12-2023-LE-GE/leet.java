class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> outGoing = new HashMap<>();
        for(List<String>  path : paths) {
            String src = path.get(0), dest = path.get(1);
            outGoing.put(src, outGoing.getOrDefault(src, 0) + 1);
            outGoing.put(dest, outGoing.getOrDefault(dest, 0));
        }

        for(Map.Entry<String, Integer> mp : outGoing.entrySet()) {
            if(mp.getValue() == 0)
                return mp.getKey();
        }
        return "";
    }
}