class Solution {
    public boolean closeStrings(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] w1 = new int[26];
        int[] w2 = new int[26];
        
        if(n != m)  return false;
        for(int i = 0; i < n; i++)
            w1[word1.charAt(i) - 'a']++;
        for(int i = 0; i < m; i++)
            w2[word2.charAt(i) - 'a']++;

        for(int i = 0; i < 26; i++)
            if(w1[i] > 0 && w2[i] == 0) return false;
            else if(w1[i] == 0 && w2[i] > 0)    return false;
            
        
        int w1Ct = 0, w2Ct = 0;
        for(int i = 0; i < w1.length; i++) {
            if(w1[i] > 0) {
                boolean got = false;
                for(int j = 0; j < w2.length; j++) {
                    if(w2[j] == w1[i]) {
                        w2Ct += w2[j];
                        w1Ct += w1[i];
                        w2[j] = 0;
                        w1[i] = 0;
                        got = true;
                        break;
                    }
                }
                if(!got)    return false;
            }
        }
        System.out.println("comp ::" + w1Ct +"::" + w2Ct);
        return w1Ct == w2Ct;
    }
}

// word1 = "cabbba", word2 = "abbccc"
// c:1     4
// a:2     4
// b:9     4

// just take care, that char count in word1, has some char in word2 with same frequency

