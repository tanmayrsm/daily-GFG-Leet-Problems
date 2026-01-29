
class Solution
{
    public int firstElementKTime(int n, int k, int[] a) { 
        Map<Integer, Integer> store = new HashMap<>();
        for(int x : a) {
            store.put(x, store.getOrDefault(x, 0) + 1);
            if(store.get(x) == k)   return x;
        }
        return -1;
    } 
}