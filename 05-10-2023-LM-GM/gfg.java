
class Solution
{   // referred soln 
    long substrCount (String S, int K) {
        return atmost(S,K)-atmost(S,K-1);
    }
    
    long atmost(String s, int k){
        int n = s.length();
        if(n == 0) return 0;
        
        int chrFre[] = new int [26];
        int distinct = 0;
        
        long count = 0;
        int left = 0;
        
        for(int i = 0; i < n; i++){
            
            chrFre[s.charAt(i) - 'a']++;
            
            if(chrFre[s.charAt(i) - 'a'] == 1) distinct++;
            
            while(distinct > k){
                chrFre[s.charAt(left) - 'a']--;
                
                if(chrFre[s.charAt(left) - 'a'] == 0) distinct--;
                
                left++;
                
            }
            
            count += i - left + 1;
        }
        
        return count;
    }
}