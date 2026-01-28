
class Solution {
    public int longestkSubstr(String s, int k) {
        // code here
        Map<Character, Integer> store = new HashMap<>();
        int l = 0, r = 0, n = s.length(), ans = 0;
        int min = Integer.MAX_VALUE; char key = '0';
        
        
        while(r < n) {
            
            char currChar = s.charAt(r);
            if(store.containsKey(currChar)) {   // store has currChar
                store.put(currChar, store.get(currChar) + 1);
            } else {    // store does not has currChar in it
                if(store.size() == k) {
                    // remove one element from store, as we cant afford more than k unique elems
                    while(l < r) {
                        char toBeRemoved = s.charAt(l);
                        int val = store.get(toBeRemoved);
                        if(val == 1) {
                            store.remove(toBeRemoved);
                            l++;
                            break;
                        }
                        store.put(toBeRemoved, val - 1);
                        l++;
                    }
                }
                store.put(currChar, 1);
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return store.size() < k ? -1 : ans;
        
    }
    
}