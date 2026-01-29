
// User function Template for Java
class Solution {    //reff
    public DLLNode sortAKSortedDLL(DLLNode head, int k) {
        // Code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        DLLNode fcur = head;
        for (int i = 0; i <= k; i++) {
            pq.add(fcur.data);
            fcur = fcur.next;
        }
        DLLNode cur = head;
        while (!pq.isEmpty()) {
            cur.data = pq.remove();
            cur = cur.next;
            if (fcur != null) {
              pq.add(fcur.data);
              fcur = fcur.next;
            }
        }
        return head;
    }
}