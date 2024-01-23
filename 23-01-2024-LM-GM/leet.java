import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int maxLength(List<String> arr) {
        int n = arr.size();
        int ans = 0;
        return solve(0, arr, new HashSet<>());
    }
    private static int solve(int curr, List<String> arr, Set<Character> currList) {
        if(curr == arr.size())   return currList.size();
        Set<Character> a1 = getSet(arr.get(curr));
        boolean take = true;
        int taker = 0, nottaker = 0;
        if(a1.size() == 0)  take = false;
        for(Character x : currList) {
            if(a1.contains(x)) {
                take = false;
                break;
            }
        }
        if(take) {
            // System.out.println(currList +" ::" + a1);
            Set<Character> oldList = new HashSet<>(currList);
            for(Character j : a1)
                currList.add(j);
            taker = solve(curr + 1, arr, currList);
            currList = oldList; // remove added items from last set
        }
        nottaker = solve(curr + 1, arr, currList);
        return Math.max(taker, nottaker);
    }
    private static Set<Character> getSet(String k) {
        int n = k.length();
        Set<Character> s = new HashSet<>();
        for(int i = 0; i < n; i++) {
            char c = k.charAt(i);
            if(s.contains(c))
                return new HashSet<>();
            s.add(c);
        }
        return s;
    }

}