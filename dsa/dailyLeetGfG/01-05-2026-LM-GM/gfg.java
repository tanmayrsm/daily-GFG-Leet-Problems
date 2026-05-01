class Solution {
    static ArrayList<Integer> kthLargest(int[] arr, int k) {
        // code here
        ArrayList<Integer> ans = new ArrayList<Integer>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < k - 1; i++) {
            ans.add(-1);
            q.offer(arr[i]);
        }
        q.offer(arr[k - 1]);
        ans.add(q.peek());
        for(int i = k; i < arr.length; i++) {
            if(!q.isEmpty() && q.peek() < arr[i]) {
                q.poll();
                q.offer(arr[i]);
            }
            if(q.isEmpty())
                q.offer(arr[i]);
            ans.add(q.peek());
        }
        return ans;
    }
}