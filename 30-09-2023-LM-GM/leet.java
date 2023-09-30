class Solution {    //referred soln
    public boolean find132pattern(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int k = Integer.MIN_VALUE;

        // one at peek is 'j' from problem
        // lastly, k != Integer.MIN_VALUE, unless, u have 'j' in ur stack
        
        for(int i = arr.length - 1; i >= 0 ; i--){
            if(arr[i] < k) return true;
            while(!stack.isEmpty() && stack.peek() < arr[i]){
                k = stack.pop();
            }
            stack.push(arr[i]);
        }
        return false;
    }
}