class Solution {
    public static void rotateDeque(Deque<Integer> dq, int type, int k) {
        // code here
        while(k-- > 0) {
            if (type == 1) {
                dq.addFirst(dq.pollLast());
            } else {
                dq.addLast(dq.pollFirst());
            }
        }
    }
}