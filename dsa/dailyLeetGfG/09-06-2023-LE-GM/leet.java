class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char ans = letters[0];
        for(int i = 0; i < letters.length; i++) {
            char currChar = letters[i];
            if(ans == target && currChar > ans)
                ans = currChar;
            else if (currChar > target && (currChar < ans || ans < target))
                ans = currChar;
        }

        return ans;
    }
}