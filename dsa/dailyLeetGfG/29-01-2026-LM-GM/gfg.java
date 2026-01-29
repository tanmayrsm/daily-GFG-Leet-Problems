class Solution {
    public String firstNonRepeating(String s) {
        // code here
        Queue<Character> q = new LinkedList<>();    // max size 26
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int[] ct = new int[26];
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - 'a';
            ct[curr]++;
            if(ct[curr] == 1) {
                q.offer(s.charAt(i));
            }
            while(!q.isEmpty() && ct[q.peek() - 'a'] > 1)
                q.poll();
            if(!q.isEmpty())
                sb.append(q.peek() + "");
            else sb.append("#");
        }
        return sb.toString();
    }
}