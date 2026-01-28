import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findTheLongestSubstring(String s) {
        // for each position, u need to find the longest valid substring at that 
        // point, by eliminating prefix from string, which is invalid
        StringBuilder sb = new StringBuilder("00000");  // bit masking for a-e-i-o-u
        Map<String, Integer> lastValid = new HashMap<>();
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                setCharMask(c == 'a' ? 0 : (c == 'e' ? 1 : (c == 'i' ? 2 : c == 'o' ? 3 : 4)), sb);
                String newMask = sb.toString();
                if (!lastValid.containsKey(newMask))
                    lastValid.put(newMask, i);
                else
                    ans = Math.max(ans, i - lastValid.get(newMask));
            } else {
                ans = ans++;
            }
        }
        return ans;
    }
    private static void setCharMask(int index, StringBuilder sb) {
        char curr = sb.charAt(index);
        if (curr == '0')    
            sb.setCharAt(index, '1');
        else sb.setCharAt(index, '0');
    }
}