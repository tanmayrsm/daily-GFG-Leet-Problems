class AllOne {
    // can easily do with treemap, where key is like Pair<String, count>, and sort treeset by count
    // however, we will use combo of hashmap and arrayList
    // why arrayList? as inc only increases by 1, I'm sure, they cant say arr.inser(0 index, element) 
    //  then arr.insert(20 index, element)
    Map<String, Integer> mp;    // stores string, count (aka, index in arrayList)
    List<Set<String>> arr;
    int size;
    public AllOne() {
        mp = new HashMap<>();
        arr = new ArrayList<>();
        size = 0;
    }
    
    public void inc(String key) {
        if(!mp.containsKey(key)) {
            mp.put(key, 0); // for count, follow 0 based index
            if(arr.isEmpty()) {
                arr.add(new HashSet());
                size++;
            }
            arr.get(0).add(key);
            return;
        }
        int oldIndex = mp.get(key);
        mp.put(key, oldIndex + 1);
        arr.get(oldIndex).remove(key);
        if(size <= oldIndex + 1) {
            arr.add(new HashSet<>());
            size++;
        }
        arr.get(oldIndex + 1).add(key);
    }
    
    public void dec(String key) {
        int index = mp.get(key);
        arr.get(index).remove(key);
        if(mp.get(key) == 0) {
            mp.remove(key);
            return;
        }
        mp.put(key, index - 1);
        arr.get(index - 1).add(key);
    }
    
    public String getMaxKey() {
        if(size == 0)   return "";
        int ptr = size - 1;
        Set<String> val = null;
        while(ptr >= 0 && (val == null || val.isEmpty())) { // get 'non empty' max element till found
            val = arr.get(ptr);
            ptr--;
        }
        if(val == null || val.isEmpty())   return "";
        Iterator<String> it = val.iterator();
        return it.next();
    }
    
    public String getMinKey() {
        if(size == 0)   return "";
        int ptr = 0;
        Set<String> val = null;
        while(ptr < size && (val == null || val.isEmpty())) {    // get 'non empty' min element till found
            val = arr.get(ptr);
            ptr++;
        }
        if(val == null || val.isEmpty())   return "";
        Iterator<String> it = val.iterator();
        return it.next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */