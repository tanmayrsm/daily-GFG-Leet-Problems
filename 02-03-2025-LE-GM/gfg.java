class Solution {
    public ArrayList<Integer> maxOfSubarrays(int arr[], int k) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        
        for (int i = k; i < arr.length; i++) {
            result.add(arr[deque.peek()]);
            
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }
            
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            
            deque.addLast(i);
        }
        
        result.add(arr[deque.peek()]);
        
        return result;
        
    }
}