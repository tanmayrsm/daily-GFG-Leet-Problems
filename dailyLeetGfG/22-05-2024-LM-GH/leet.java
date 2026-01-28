class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(0,res, new ArrayList<String>(), s);
        return res;
    }
    
    public void dfs(int start, List<List<String>> res, List<String> currentList, String s){
        if(start >= s.length())
            res.add(new ArrayList<String>(currentList));
        for(int i = start; i < s.length(); i++) {
            if(isPalin(s,start,i)){
                currentList.add(s.substring(start, i + 1)); 
                // added current prefixed palin string;
                // check for palin in substring i+1
                dfs(i+1, res, currentList, s);
                currentList.remove(currentList.size() - 1); // remove last result
            }
        }
    }
    
    boolean isPalin(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }
}