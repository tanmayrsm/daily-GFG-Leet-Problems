class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        String[] ans = new String[n];
        for(int i = 0; i < n; i++)
            tm.put(score[i], i);
        int currRank = n;
        for(Map.Entry<Integer, Integer> entry : tm.entrySet()) {
            String value = null;
            switch(currRank) {
                case 1 : value = "Gold Medal";
                            break;
                case 2 : value = "Silver Medal";
                            break;
                case 3 : value = "Bronze Medal";
                            break;
                default : value = String.valueOf(currRank);
                            break;
            }
            ans[entry.getValue()] = value;
            currRank--;
        }

        return ans;
    }
}