class Solution {    // reff
    public int takeCharacters(String s, int k) {
        int[] charCount = new int[3];
        char[] chars = s.toCharArray();
        int currentIndex, length = chars.length;
        
        // Count characters until we have at least k of each
        for (currentIndex = 0; currentIndex < length; currentIndex++) {
            charCount[chars[currentIndex] - 'a']++;
            if (charCount[0] >= k && charCount[1] >= k && charCount[2] >= k) {
                break;
            }
        }
        
        // If we haven't found k of each character, return -1
        if (currentIndex == length) {
            return -1;
        }
        
        // Initialize count and minimum count
        int count = currentIndex + 1;
        int minCount = count;
        int endIndex = length - 1;
        
        // Adjust the count and find the minimum
        while (currentIndex >= 0) {
            if (charCount[chars[currentIndex] - 'a'] == k) {
                while (chars[currentIndex] != chars[endIndex]) {
                    charCount[chars[endIndex] - 'a']++;
                    endIndex--;
                    count++;
                }
                endIndex--;
            } else {
                charCount[chars[currentIndex] - 'a']--;
                count--;
                minCount = Math.min(count, minCount);
            }
            currentIndex--;
        }
        
        return minCount;
    }
}