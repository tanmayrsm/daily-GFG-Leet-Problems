class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<String>> mp = new HashMap<>();
        char[] curr = bottom.toCharArray();
        int n = bottom.length(), m = n;

        for(String a : allowed) {
            String key = a.substring(0, 2), value = a.substring(2, 3);
            if(!mp.containsKey(key))
                mp.put(key, new HashSet<>());
            mp.get(key).add(value);
        }

        char[] next = new char[n - 1];
        return solve(curr, next, n, 1, mp);

    }
    private boolean solve(char[] curr, char[] next, int level, int idx, Map<String, Set<String>> mp) {

        if (level == 1)  return true;
        if (idx == level) {
            char[] next2 = new char[level - 1];
            return solve(next, next2, level - 1, 1, mp);
        }
        String key = curr[idx - 1] + "" + curr[idx];
        if(mp.containsKey(key)) {
            for(String val : mp.get(key)) {
                next[idx - 1] = val.charAt(0);
                if (solve(curr, next, level, idx + 1, mp))
                    return true;
            }
        }
        return false;
    }
}