class LRUCache {    // ref soln
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        cap = capacity;
    }
    
    public void makeRecently(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }


    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key,value);
            return;
        }
        if (cache.size() >= cap) {
            Integer oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
            
        }
        cache.put(key,value);
    }

}