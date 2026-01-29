class Solution{
    public static int powerfullInteger(int n,int interval[][],int k) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for(int[] x : interval) {
            int l = x[0];
            int r = x[1] + 1;
            if(m.get(l) == null) {
                m.put(l, 1);
            } else {
                m.put(l, m.get(l) + 1);
            }
            if(m.get(r) == null) {
                m.put(r, -1);
            } else {
                m.put(r, m.get(r) - 1);
            }
        }
        int curr = 0;
        int maxi = -1;
        boolean flag = false;
        
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            curr += entry.getValue();
            if(curr >= k) {
                flag = true;
                maxi = entry.getKey();
            } else if(flag == true) {
                maxi = entry.getKey() - 1;
                flag = false;
            } else {
                flag = false;
            }
        }
        return maxi;
    }
}