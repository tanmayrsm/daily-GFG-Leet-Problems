class Solution {
    public void rearrangeQueue(Queue<Integer> q) {
        // code here
        int n = 0, half = 0, first = 0;
        List<Integer> temp = new ArrayList<>();
        while(!q.isEmpty()) {
            temp.add(q.poll());
        }
        n = temp.size();
        half = n / 2;
        while(first < n / 2) {
            q.offer(temp.get(first++));
            q.offer(temp.get(half++));
        }
        return ;
    }
}
