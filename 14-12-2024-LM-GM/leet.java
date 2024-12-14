import java.util.PriorityQueue;

class Solution {    // reff
    public long continuousSubarrays(int[] nums) {
        long count = 0;
        int start = 0;
        int end = 0;
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((a,b)->b-a);
        while (end < nums.length) {
            max.offer(nums[end]);
            min.offer(nums[end]);
            while(max.peek()-min.peek()>2){
                max.remove(nums[start]);
                min.remove(nums[start]);
                start++;
            }
            count += end-start+ 1;
            end++;
        }
        return count;
    }
}
