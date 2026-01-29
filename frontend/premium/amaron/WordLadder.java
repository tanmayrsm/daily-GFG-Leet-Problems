import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> wordVisited = new HashSet<>();
        int ans = 0;
        if(!wordSet.contains(endWord))  return 0;
        Queue<String> q = new LinkedList<>();
        while (!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                String curr = q.poll();
                if (curr.equals(endWord))
                    return ans;
                int m = curr.length();
                StringBuilder sb = new StringBuilder(curr);
                StringBuilder sbCpy = new StringBuilder(curr);
                for (int i = 0; i < m; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        if (!wordVisited.contains(sb.toString()) && wordSet.contains(sb.toString())) {
                            q.offer(new String(sb.toString()));
                            wordVisited.add(sb.toString());
                        }
                        sb = new StringBuilder(sbCpy);
                    }
                }
            }
            ans++;
        }
    }
}