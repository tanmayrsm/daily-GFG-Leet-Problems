class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> ct = new HashMap<>();
        int n = s.length(), ans = 0, biggestOdd = 0;
        boolean isFirstOdd = false;
        for(int i = 0; i < n; i++)
            ct.put(s.charAt(i), ct.getOrDefault(s.charAt(i), 0) + 1);
        
        for(Map.Entry<Character, Integer> item : ct.entrySet()) {
            int x = item.getValue();
            if ( x % 2 == 0)    ans += x;
            else {
                ans += x;
                if (!isFirstOdd) {
                    isFirstOdd = true;
                } else {
                    ans -= 1;
                }
            }
        }
        return ans + biggestOdd;
    }
}