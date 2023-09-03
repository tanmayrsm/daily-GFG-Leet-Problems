class MyHashSet {
    private Map<Integer, Boolean> map;
    public MyHashSet() {
        map = new HashMap<>();
    }
    
    public void add(int key) {
        map.put(key, true);
    }
    
    public void remove(int key) {
        map.remove(key);
    }
    
    public boolean contains(int key) {
        return map.get(key) != null ? true : false;
    }
}