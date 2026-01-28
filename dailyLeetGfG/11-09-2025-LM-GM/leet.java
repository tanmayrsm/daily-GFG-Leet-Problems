class Solution {
    public String sortVowels(String s) {
        HashSet<Character> vowels = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] chars = s.toCharArray();
        List<Character> vowelsChars = new ArrayList<>();
        for (char c : chars) {
            if (vowels.contains(c)) {
                vowelsChars.add(c);
            }
        }
        Collections.sort(vowelsChars);

        for (int i = 0, j = 0; i < chars.length && j < vowelsChars.size(); i++) {
            if( vowels.contains(chars[i])) {
                chars[i] = vowelsChars.get(j);
                j++;
            }
        }
        return new String(chars);
    }
}