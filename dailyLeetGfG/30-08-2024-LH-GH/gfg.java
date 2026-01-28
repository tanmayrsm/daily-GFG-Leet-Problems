
// User function Template for Java

class Solution {
    private static ArrayList<ArrayList<Integer>> ans;
    public ArrayList<ArrayList<Integer>> nQueen(int n) {
        // code here
        ans = new ArrayList<>();
        Map<Integer, Integer> mp = new LinkedHashMap<>();

        for (int j = 1; j <= n; j++) {  // try each column first row
            mp.put(1, j);
            func(2, mp, n);
        }
        
        return ans;
    }
    private static boolean func(int rowNo, Map<Integer, Integer> mp, int n) {
        if (rowNo == n + 1) {
            ans.add(new ArrayList<>(mp.values()));
            return true;
        }
        for (int j = 1; j <= n; j++) {
            if (isValid(rowNo, j, mp)) {
                mp.put(rowNo, j); 
                func(rowNo + 1, mp, n);
                mp.remove(rowNo);
            }
        }
        return false;
    }
    private static boolean isValid(int X, int Y, Map<Integer, Integer> mp) {
        for (Map.Entry<Integer, Integer> x : mp.entrySet()) {
            int curX = x.getKey(), curY = x.getValue();
            if (curX == X || curY == Y || Math.abs(curX - X) == Math.abs(curY - Y))
                return false;
        }
        return true;
    }
}