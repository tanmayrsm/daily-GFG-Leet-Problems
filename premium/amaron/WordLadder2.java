
class Solution {
    private static Set<String> wordSet;
    private static List<List<String>> ans;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordSet = new HashSet<>();
        ans = new ArrayList<>();
        for (String word : wordList)
            wordSet.add(word);
        solve(beginWord, new LinkedHashSet<>(), endWord);
        return ans;
    }
    private static void solve(String curr, Set<String> currList, String target) {
        if (curr.equals(target)) {
            if (!ans.isEmpty()) {
                if (ans.get(0).size() > currList.size()) {
                    ans = new ArrayList<>();
                }
            }
            ans.add(new ArrayList<>(currList));
            return;
        }
        if ((!ans.isEmpty() && ans.get(0).size() < currList.size()) || currList.contains(curr)) {
            return;
        }

        char[] arr = curr.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            for (char x = 'a'; x <= 'z'; x++) {
                char temp = arr[i];
                arr[i] = x;
                String tmp = new String(arr);
                if (wordSet.contains(tmp)) {
                    currList.add(tmp);
                    solve(tmp, currList, target);
                    currList.remove(tmp);
                }
                arr[i] = temp;
            }
        }

    }


}