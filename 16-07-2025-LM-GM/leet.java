class Solution {
    public int maximumLength(int[] nums) {
        // problem states we need to have pairs from start and end, where sum of them has to be all even or all odd
        int oddCt = 0, evenCt = 0;
        Stack<Integer> st = new Stack<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                evenCt++;
                while (!st.isEmpty() && st.peek() == 0) st.pop();
                st.push(0);
            }
            else {
                oddCt++;
                while (!st.isEmpty() && st.peek() == 1) st.pop();
                st.push(1);
            }
        }
        return Math.max(oddCt, Math.max(evenCt, st.size()));

    }
}