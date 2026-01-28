import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    class Pair {
        char c;
        int idx;
        Pair(int idx, char c) {
            this.idx = idx;
            this.c = c;
        }
    }
    public String clearStars(String s) {
        int n = s.length(), m = 0;
        Set<Integer> removedIdx = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            if (a.c == b.c)
                return Integer.compare(a.idx, b.idx);
            return Character.compare(a.c, b.c);
        });

        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (curr == '*') {
                int removed = pq.poll();
                removedIdx.add(i);
                removedIdx.add(removed);
            } else pq.offer(new Pair(i, curr));
        }

        for (int i = 0; i < n; i++) {
            if (!removedIdx.contains(i))
                sb.append(s.charAt(i) + "");
        }
        return sb.toString();

    }
}