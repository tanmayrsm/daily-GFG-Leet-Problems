class SmallestInfiniteSet {
    TreeSet<Integer> ts;
    public SmallestInfiniteSet() {
        ts = new TreeSet<>();
        for(int i = 1; i <= 1000; i++)
            ts.add(i);
    }
    
    public int popSmallest() {
        if(ts.size() == 0)
            return 0;
        int smallest = ts.first();
        // System.out.println("::" + smallest);
        ts.remove(smallest);
        return smallest;
    }
    
    public void addBack(int num) {
        ts.add(num);
    }
}