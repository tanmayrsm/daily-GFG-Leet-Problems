
class Solution
{
    public static ArrayList<Integer> repeatedRows(int matrix[][], int m, int n) {
        //code here
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Map<String, Integer> mp = new HashMap<>();
        
        for(int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                sb.append(String.valueOf(matrix[i][j]));
            }
            String r = sb.toString();
            if(mp.containsKey(r)) {
                ans.add(i);
            } else mp.put(r, i);
        }
        
        return ans;
    }
}