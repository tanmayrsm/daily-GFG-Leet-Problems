class Solution {
    public void reverseQueue(Queue<Integer> queue) {
        // code here
        Stack<Integer> stack = new Stack<>();

        while(!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
}