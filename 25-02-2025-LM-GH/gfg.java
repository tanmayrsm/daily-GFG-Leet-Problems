class Solution {
    public static int getMaxArea(int arr[]) {
        int n = arr.length;
        if(n == 1) return arr[0];
        
        int res = 0;
        Stack<Pair> st = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            int num = arr[i];
            int cnt = 0;
            while(!st.isEmpty() && st.peek().a >= num) {
                Pair p = st.pop();
                cnt += p.b;
                res = Math.max(res, p.a * cnt);
            }
            st.push(new Pair(num, cnt + 1));
        }

        int cnt = 0;
        while(!st.isEmpty()) {
            Pair p = st.pop();
            cnt += p.b;
            res = Math.max(res, p.a * cnt);
        }
        
        return res;
    }
    
    static class Pair {
        int a;
        int b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}