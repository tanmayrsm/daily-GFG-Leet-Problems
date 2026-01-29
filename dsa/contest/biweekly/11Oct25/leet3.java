class ExamTracker {
    class Pair {
        int time;
        long score;
        long prefixScore;
        Pair(int time, long score, long prefixScore) {
            this.time = time;
            this.score = score;
            this.prefixScore = prefixScore;
        }
    }
    List<Pair> arr;
    int n;
    public ExamTracker() {
        arr = new ArrayList<>();
        n = 0;
    }

    public void record(int time, int score) {
        Pair p = new Pair(time, score, (n == 0) ? score : (score + arr.get(n - 1).prefixScore));
        arr.add(p);
        n++;
    }

    public long totalScore(int startTime, int endTime) {
        int greterThanStart = getGreaterEqual(startTime);
        int lessThanend = getSmallerEqual(endTime);
        if (greterThanStart == -1 || lessThanend == -1) return 0;
        long end = arr.get(lessThanend).prefixScore;
        long start = (greterThanStart - 1 >= 0 ?
                arr.get(greterThanStart - 1).prefixScore : 0);
        return end - start;
    }

    private int getGreaterEqual(int time) {
        if(arr.isEmpty() || arr.get(0).time > time) return -1;
        int l = 0, r = n - 1, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if(arr.get(mid).time >= time) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1; // arr -> 14, time -> 8
        }
        return ans;
    }

    private int getSmallerEqual(int time) {
        if(arr.isEmpty() || arr.get(0).time > time) return -1;
        int l = 0, r = n - 1, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if(arr.get(mid).time <= time) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1; // arr-> 8, time -> 14
        }
        return ans;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */

Example 1:

Input:
        ["ExamTracker", "record", "totalScore", "record", "totalScore", "totalScore", "totalScore", "totalScore"]
        [[], [1, 98], [1, 1], [5, 99], [1, 3], [1, 5], [3, 4], [2, 5]]

Output:
        [null, null, 98, null, 98, 197, 0, 99]

Explanation

ExamTracker examTracker = new ExamTracker();
examTracker.record(1, 98); // Alice takes a new exam at time 1, scoring 98.
examTracker.totalScore(1, 1); // Between time 1 and time 1, Alice took 1 exam at time 1, scoring 98. The total score is 98.
examTracker.record(5, 99); // Alice takes a new exam at time 5, scoring 99.
examTracker.totalScore(1, 3); // Between time 1 and time 3, Alice took 1 exam at time 1, scoring 98. The total score is 98.
examTracker.totalScore(1, 5); // Between time 1 and time 5, Alice took 2 exams at time 1 and 5, scoring 98 and 99. The total score is 98 + 99 = 197.
examTracker.totalScore(3, 4); // Alice did not take any exam between time 3 and time 4. Therefore, the answer is 0.
examTracker.totalScore(2, 5); // Between time 2 and time 5, Alice took 1 exam at time 5, scoring 99. The total score is 99.


Constraints:

        1 <= time <= 10^9
        1 <= score <= 10^9
        1 <= startTime <= endTime <= t, where t is the value of time from the most recent call of record().
Calls of record() will be made with strictly increasing time.
After ExamTracker(), the first function call will always be record().
At most 10^5 calls will be made in total to record() and totalScore().