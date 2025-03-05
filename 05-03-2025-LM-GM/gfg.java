class Solution {
    public int longestStringChain(String words[]) {
        // code here
        Arrays.sort(words,(a,b)-> a.length() - b.length());
      
      // rather than below, u can use 2 pointers to check if wordB has only one mismatching charatcer with wordA, make a 
      // seprate function for that and return true, false
      //   - cond1 - wordA length + 1 == wordB lenggt
      //.  - cond2 - only 1 mismatch character in wordB
      
        Map<String,Integer> dp = new HashMap<>();
        int maxLen=0;
        
        for(String word : words)
        {
            int best =0;
            for(int i=0;i<word.length();i++)
            {
                String prediction = word.substring(0,i)+ word.substring(i+1);
                best = Math.max(best, dp.getOrDefault(prediction,0)+1);
            }
            dp.put(word,best);
   
            maxLen=Math.max(maxLen,best);
        }
        return maxLen;
    }
}