class CustomStack {
    int[] arr;
    int tos, capacity;
    public CustomStack(int maxSize) {
        arr = new int[maxSize];
        this.tos = 0;
        this.capacity = maxSize;
    }
    
    public void push(int x) {
        if(tos == capacity) return;
        arr[tos++] = x;
    }
    
    public int pop() {
        if(tos == 0)    return -1;
        int res = arr[tos - 1];
        tos--;
        return res;
    }
    
    public void increment(int k, int val) {
        for(int i = 0; i < Math.min(tos, k); i++) {
            arr[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */