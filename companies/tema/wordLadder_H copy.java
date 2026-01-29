class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        char[] s = beginWord.toCharArray(), e = endWord.toCharArray();
        Set<String> st = new HashSet<>();
        int n = s.length, m = e.length, ans = 1, qSize = 1;
        Queue<char[]> q = new LinkedList<>();

        for(String word : wordList)
            st.add(word);
        if (n != m || !st.contains(endWord) || beginWord.equals(endWord)) return 0;

        q.offer(s);

        while(!q.isEmpty()) {
            int oldSize = qSize;
            qSize = 0;
            while(oldSize-- > 0) {
                char[] curr = q.poll();
                if (isEqual(curr, e))   return ans;
                for (int i = 0; i < n; i++) {
                    char older = curr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        curr[i] = c;
                        String newWord = new String(curr);
                        if (c != older && st.contains(newWord)) {
                            st.remove(newWord);
                            q.offer(newWord.toCharArray());
                            qSize++;
                        }
                    }
                    curr[i] = older;
                }
            }
            ans++;
        }
        return 0;
    }

    private boolean isEqual(char[] a, char[] b) {
        if(a.length != b.length)    return false;
        int n = a.length;
        for (int i = 0; i < n; i++)
            if (a[i] != b[i])
                return false;
        return true;
    }
}