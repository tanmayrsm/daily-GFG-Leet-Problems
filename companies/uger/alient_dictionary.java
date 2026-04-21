class Solution {
    public String alienOrder(String[] words) {
        int n = words.length;
        if(n == 1)  return words[0];
        int[] inorder = new int[26];
        List<Set<Integer>> adj = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i <26; i++)  adj.add(new HashSet<>());
        Arrays.fill(inorder, -1);
        
        for(int i = 1; i < n; i++) {
            int l1 = words[i - 1].length(), l2 = words[i].length(), x = 0, y = 0;
            while (x < l1 && y < l2 && words[i - 1].charAt(x) == words[i].charAt(y)) {
                x++;
                y++;
                if(x > 1)
                    addEdge(adj, words[i - 1].charAt(x - 1), words[i - 1].charAt(x), inorder);
                if(y > 1)
                    addEdge(adj, words[i].charAt(y - 1), words[i].charAt(y), inorder);
            }
            if(x == l1 && y < l2) return "";  // invalid palindrome
            else if (x < l1 && y < l2) 
                addEdge(adj, words[i - 1].charAt(x), words[i].charAt(y), inorder);
        }
        
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == 0)    q.offer(i);
        }
        while(!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                int ch = q.poll();
                for(Integer ch : adj.get(ch)) {
                    inorder[ch]--;
                    if(inorder[ch] == 0)
                        q.offer(ch);
                }
                sb.append((ch + 'a') + "");
            }
        }
        for(int x : inorder)
            if(x > 0)   return "";
        return sb.toString();
    }
    private void addEdge(List<Set<Integer>> adj, char from, char to, int[] inorder) {
        int F = from - 'a', T = to - 'a';
        adj.get(F).add(T);
        if(inorder[F] == -1)    inorder[F] = 0;
        if(inorder[T] == -1)   inorder[T] = 0;
        inorder[T]++;
    }
}