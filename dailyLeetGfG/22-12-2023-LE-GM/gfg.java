
class Solution {
    public static ArrayList<Integer> maxMeetings(int N, int[] S, int[] F) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();

        PriorityQueue<Meeting> pq = new PriorityQueue<>((Meeting t1, Meeting t2) -> {
            if (t1.endTime != t2.endTime)
                return Integer.compare(t1.endTime, t2.endTime);
            // If endTime is the same, compare based on startTime in descending order
            return Integer.compare(t2.startTime, t1.startTime);
        });

        for(int i = 0; i < N; i++)
            pq.offer(new Meeting(S[i], F[i], i));

        int lastPickedEndTime = Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            Meeting curr = pq.poll();
            if(lastPickedEndTime < curr.startTime) {
                ans.add(curr.index + 1);
                lastPickedEndTime = curr.endTime;
            }
        }
        Collections.sort(ans);
        return ans;
    }
    static class Meeting {
        int startTime;
        int endTime;
        int index;
        Meeting(int s, int e, int i) {
            this.startTime = s;
            this.endTime = e;
            this.index = i;
        }
    }


}