class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> st = new HashSet<> (dictionary);
        String[] words = sentence.split(" ");
        List<String> ans = new ArrayList<>();

        for(String word : words) {
            StringBuilder sb = new StringBuilder();
            int n = word.length();
            boolean attach = false;
            for(int i = 0; i < n; i++) {
                sb.append(word.charAt(i) + "");
                if(st.contains(sb.toString())) {
                    ans.add(sb.toString());
                    attach = true;
                    break;
                }
            }
            if(!attach)
                ans.add(sb.toString());
        }

        return String.join(" ", ans);
    }
}