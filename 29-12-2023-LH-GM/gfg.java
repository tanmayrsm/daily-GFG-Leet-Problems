
class Solution
{
    int kSubstrConcat(int n, String s, int k)
    {
        // Your Code Here
        if(n % k != 0 || k > n)  return 0;
        
        Map<String, Integer> store = new HashMap<>();
        for(int i = 0; i < n; i += k) {
            String curr = s.substring(i, i + k);
            store.put(curr, store.getOrDefault(curr, 0) + 1);
        }
        if(store.size() <= 2) {
            int ct = 0;
            for(Map.Entry<String, Integer> mp : store.entrySet()) {
                if(mp.getValue() == 1)  ct++;
            }
            if(ct <= 2) return 1;
        }
        return 0;
    }
}