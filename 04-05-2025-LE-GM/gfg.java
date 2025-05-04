
class Solution {
    public int findSubString( String str) {
        // Your code goes here
        int n = str.length(), ans = Integer.MAX_VALUE;
        Set<Character> dist = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        for(int i = 0; i < n; i++) 
            dist.add(str.charAt(i));
        int uniqueSize = dist.size();
        
        while(r < n) {
            while(r < n && map.size() < uniqueSize) {   // map size wont be more than 256
                char curr = str.charAt(r);
                if(map.get(curr) == null)
                    map.put(curr, 1);
                else    map.put(curr, map.get(curr) + 1);
                r++;
            }
            ans = Math.min(ans, r - l);
            // r++;
            // System.out.println("ans :: " + ans + " :: " + map.size() + " :: l :: " + l + " :: r :: " + r);
            while(l < n && l < r && map.size() == uniqueSize) {
                char curr = str.charAt(l);
                // System.out.println(curr + " :: " + map.size());
                if(map.get(curr) == null)   break;
                if(map.get(curr) == 1) {
                    ans = Math.min(ans, r - l);
                    map.remove(curr);
                } 
                else map.put(curr, map.get(curr) - 1);
                l++;
            }
        }
        return ans;
    }
}