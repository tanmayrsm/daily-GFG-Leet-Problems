class UndergroundSystem {
    class CheckInEntry {
        public String from;
        public Integer time;
        public CheckInEntry(String f, int t) {
            from = f;
            time = t;
        }
    }
    
    Map<Integer, CheckInEntry> map;
    Map<String, List<Integer>> fromToEntries;

    public UndergroundSystem() {
        map = new HashMap<>();
        fromToEntries = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        map.put(id, new CheckInEntry(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInEntry c = map.get(id);
        if(c != null) {
            String fromStation = c.from;
            int startTime = c.time;
            int diff = t - startTime;
            String entry = fromStation + "#" + stationName;
            if(fromToEntries.get(entry) == null) {
                fromToEntries.put(entry, new ArrayList<>());
            }
            fromToEntries.get(entry).add(diff);
        }
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "#" + endStation;
        int n = fromToEntries.get(key).size();
        int sum = fromToEntries.get(key).stream()
            .mapToInt(i -> i)
            .sum();
        return (double)((double)sum /  (double) n);
    }
}
/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */