class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        StringBuilder currStr = new StringBuilder("");

        for(int i = 0; i < n; i++) {
            String word = words[i];
            
            // if currStr has one word, and 
            if( maxWidth - 
                (currStr.length() + word.length() +     // check if curr word can fit
                (currStr.length() > 0 ? 1 : 0))         // ternary condition, for counting space length , if more some word already in currStr
            >= 0) {
                if(i > 0)
                    currStr.append(" ");                // if this word fits, add a space, and add word
                currStr.append(word);
            } else {
                // if not, rearrange this string as centered justified, and add it to ans
                String res = reArrange(currStr.toString(),Math.abs(maxWidth - currStr.length()), false);
                ans.add(res);
                currStr = new StringBuilder(word);
            }
        }
        // ensure to justify last sentence as left justify
        ans.add(reArrange(currStr.toString(), Math.abs(maxWidth - currStr.length()), true));
        return ans;
    }

    private static String reArrange(String sb, int width, boolean leftjustified) {
        String[] s = sb.split(" ");
        
        int n = s.length;
        int reqWidth = width +( leftjustified ? 0 : (s.length - 1));
        
        int i = leftjustified ? n - 1 : 0;

        while(reqWidth > 0) {
            s[i] += " ";

            if(!leftjustified) {    // hop to next index, to add space there, if centered justify
                i++;
                if(i >= n - 1)
                    i = 0;
            }
            else if (leftjustified) // hop to last index, to add space, if left justified
                i = n - 1;
            reqWidth--;
        }

        return leftjustified ? String.join(" ", s) : String.join("", s);
    }
}