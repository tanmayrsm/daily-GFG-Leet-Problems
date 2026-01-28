
class Solution {
    // Finds decimal value of a given roman numeral
    public int romanToDecimal(String str) {
        // code here
        int ans = 0, n = str.length(), prevNo = -1;
        Map<Character, Integer> m = Map.of (
            'I',1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);
        for(int i = 0; i < n; i++) {
            int curr = m.get(str.charAt(i));
            if(prevNo != -1 && prevNo < curr) {
                ans = Math.abs(ans - curr);
            }   else ans += curr;
            prev = curr;
        }
        return ans;
    }
}