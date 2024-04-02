class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> mp = new HashMap<>();

        for(int i = 0; i < n; i++) {
            char S = s.charAt(i), T = t.charAt(i);
            if (mp.containsKey(S)) {
                if (mp.get(S) != T)
                    return false;
            } else {
                if (mp.containsValue(T))
                    return false;
                mp.put(S, T);
            }
        }
        return true;
    }
}