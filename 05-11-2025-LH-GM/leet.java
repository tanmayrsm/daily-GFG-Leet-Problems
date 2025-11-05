import java.util.*;

class Pair {
    long count;
    int num;
    Pair(long c, int n) { count = c; num = n; }
}

class PairComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        if (a.count != b.count)
            return Long.compare(b.count, a.count);  // higher count first
        if (a.num != b.num)
            return Integer.compare(b.num, a.num);   // higher num first
        return 0;
    }
}

class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        List<Long> result = new ArrayList<>();
        Map<Integer, Long> numCount = new HashMap<>();

        TreeSet<Pair> top = new TreeSet<>(new PairComparator());
        TreeSet<Pair> bottom = new TreeSet<>(new PairComparator());

        long runningSum = 0;

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            long count = numCount.getOrDefault(num, 0L);

            Pair oldPair = new Pair(count, num);
            if (count > 0) {
                if (top.contains(oldPair)) {
                    top.remove(oldPair);
                    runningSum -= count * num;
                } else {
                    bottom.remove(oldPair);
                }
            }

            count++;
            numCount.put(num, count);
            Pair newPair = new Pair(count, num);
            top.add(newPair);
            runningSum += count * num;

            if (top.size() > x) {
                Pair smallest = top.last();
                runningSum -= smallest.count * smallest.num;
                bottom.add(smallest);
                top.remove(smallest);
            }

            if (i >= k) {
                int oldNum = nums[i - k];
                long oldCount = numCount.get(oldNum);

                Pair oldPair2 = new Pair(oldCount, oldNum);
                if (top.contains(oldPair2)) {
                    top.remove(oldPair2);
                    runningSum -= oldCount * oldNum;
                } else {
                    bottom.remove(oldPair2);
                }

                oldCount--;
                numCount.put(oldNum, oldCount);
                if (oldCount > 0) {
                    bottom.add(new Pair(oldCount, oldNum));
                }

                if (top.size() < x && !bottom.isEmpty()) {
                    Pair best = bottom.first();
                    top.add(best);
                    bottom.remove(best);
                    runningSum += best.count * best.num;
                }
            }

            if (i + 1 >= k) {
                result.add(runningSum);
            }
        }
        return result.stream().mapToLong(Long::longValue).toArray();
    }
}