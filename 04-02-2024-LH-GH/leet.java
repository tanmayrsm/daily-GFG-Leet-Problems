class Solution {
    public String minWindow(String s, String t) {
        String ans = "";
        int ansLen = Integer.MAX_VALUE;

        int n = s.length(), m = t.length();
        if(m > n)   return ans;
        Map<Character, Integer> tt = new HashMap<>(26);   // max size will be 26
        Map<Character, Integer> ss = new HashMap<>(26);   // max size will be 26
        
        // construct map for 't'
        for(int i = 0; i < m; i++) {
            char c = t.charAt(i);
            if(!tt.containsKey(c))
                tt.put(c, 1);
            else tt.put(c, tt.get(c) + 1);
        }

        int l = 0, r = 0;
        int ansL = 0, ansR = 0;

        // sliding window
        // l to r will always be valid substring which will have all chars in 't' in them
        while(r < n) {
            int oldR = r;
            while(r < n && !satisfy(tt, ss)) {  // go till right, unless all chars in 't' are not satisfied
                char c = s.charAt(r);
                if(!ss.containsKey(c))
                    ss.put(c, 1);
                else ss.put(c, ss.get(c) + 1);
                r++;
            }
            if(r - l < ansLen && satisfy(tt, ss)) { // update answer
                ansL = l; ansR = r + 1;
                ansLen = r - l;
            }
            
            int oldL = l;
            while(l < r && satisfy(tt, ss)) {   // go till right, until all chars in 't' are satisfied
                char c = s.charAt(l);
                if(ss.get(c) == 1)
                    ss.remove(c);
                else    ss.put(c, ss.get(c) - 1);
                l++;
            }

            if(r - l < ansLen && oldL != l) {   // update answer
                ansL = l - 1; ansR = r;
                ansLen = r - l;
            }
        }

        if(ansR != 0)
            ans = s.substring(ansL, ansR);
        return ans;
    }

    // function to check if all keys in tgt are in curr, and values of all keys in tgt <= values of same keys in curr
    private static boolean satisfy(Map<Character, Integer> tgt, Map<Character, Integer> curr) {
        for(Map.Entry<Character, Integer> x : tgt.entrySet()) {
            char key = x.getKey(); int val = x.getValue();
            if(curr.get(key) == null || curr.get(key) < val) {
                return false;
            }
        }
        return true;
    }
}