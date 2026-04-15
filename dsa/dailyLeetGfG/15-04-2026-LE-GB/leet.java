class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length, ans = Integer.MAX_VALUE, ct = 0;

        if(words[startIndex].equals(target)) return 0;

        int right = (startIndex + 1) % n;
        for(int i = right; i != startIndex; i = (i + 1) % n) {
            ct++;
            if(words[i].equals(target)) {
                ans = Math.min(ans, ct);
            }
        }

        ct = 0;
        int left = (startIndex - 1 + n) % n;
        for(int i = left; i != startIndex; i = (i - 1 + n) % n) {
            ct++;
            if(words[i].equals(target))
                ans = Math.min(ans, ct);
        }
        if(ans == Integer.MAX_VALUE)    return -1;
        return ans;
    }
}