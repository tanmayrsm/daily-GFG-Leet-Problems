class Solution {
    class Pair {
        char ch;
        int ct;
        int nextTime;
        Pair (char ch, int ct, int nextTime) {
            this.ch = ch;
            this.ct = ct;
            this.nextTime = nextTime;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            if (a.ct == b.ct)
                return Integer.compare(a.nextTime, b.nextTime);
            return Integer.compare(b.ct, a.ct);
        });
        int endTime = 1, currTime = 1;
        int[] count = new int[26];
        for(char t : tasks)
            count[t - 'A']++;

        for (int i = 0; i < count.length; i++) {
            // System.out.println(i + "::" + count[i]);
            if (count[i] > 0)
                pq.offer(new Pair((char)(i + 'A'), count[i], -1));
        }
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            curr.ct--;
            if (curr.nextTime < currTime)
                curr.nextTime = currTime;
            endTime = Math.max(endTime, curr.nextTime);
            if(curr.ct > 0) {
                curr.nextTime += n + 1;
                // System.out.println(curr.ch + "::" + curr.nextTime + "::" + curr.ct);
                pq.offer(curr);
            }
            currTime++;
        }
        return endTime;
    }
}