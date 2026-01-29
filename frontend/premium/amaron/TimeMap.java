import java.util.ArrayList;
import java.util.List;

class TimeMap {
    class Pair {
        Integer ts;
        String value;
        Pair (int ts, String value) {
            this.ts = ts;
            this.value = value;
        }
    }
    Map<String, List<Pair>> mp;
    public TimeMap() {
        mp = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!mp.containsKey(key))
            mp.put(key, new ArrayList<>());
        mp.get(key).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if(mp.isEmpty() || mp.get(key).isEmpty())
            return "";
        Pair ans = getPair(key, timestamp);
        return ans == null ? "" : ans.value;
    }

    private Pair getPair(int key, int ts) {
        List<Pair> list = mp.get(key);
        Pair ans = null;
        if (list.get(0).ts >= ts)   return ans;
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid).ts < ts) {
                ans = list.get(mid);
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */