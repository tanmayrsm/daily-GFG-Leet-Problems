class Solution{
    private static Map<String, Integer> map;
    private static int ans;
    public int duplicateSubtreeNaryTree(Node root){
       // Code here
       map = new HashMap<>();
       ans = 0;
       dfs(root);
       return ans;
    }
    private static String dfs(Node x) {
        if(x == null) {
            return "";
        }
        StringBuilder curr = new StringBuilder("");
        if(x.children != null && x.children.size() > 0) {
            for(Node r : x.children) {
                curr.append(dfs(r));
            }
        }
        curr.append(x.data);
        String j = curr.toString();
        if(map.get(j) == null) {
            map.put(j, 1);
        } else if(map.get(j) != null && map.get(j) == 1) {
            ans++;
            map.put(j, map.get(j) + 1);
        } else {
            map.put(j, map.get(j) + 1);
        }
        return j;
    }
    
}