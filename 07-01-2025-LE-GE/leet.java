class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        String str = String.join(" ", words);

        for (int i = 0; i < words.length; i++) {
            if (str.indexOf(words[i]) != str.lastIndexOf(words[i])) {
                res.add(words[i]);
            }
        }

        return res;
    }
}