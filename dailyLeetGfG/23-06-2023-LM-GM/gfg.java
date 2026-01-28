
class Solution {
    static int leastInterval(int N, int k, char tasks[]) {
        // code here
        int[] ct = new int[27];
        int maxFreq = 0;
        int ans = 0;
        int elementsWithMaxFreq = 0;
        
        for(char c : tasks) {
            ct[c - 'A']++;
            maxFreq = Math.max(maxFreq, ct[c - 'A']);
        }
        
        ans = (maxFreq * (k + 1)) - k;  
        // if A -> 4, and k -> 2 , then 'A' needs min 
        // 4 * (2+1) => 12 - last k idle states => 12 - 2 => 10 space
        
        
        for(int i = 0; i < 27; i++) {
            int x = ct[i];
            if(x == maxFreq) {
                elementsWithMaxFreq++;
                
            }
            // System.out.println((char)(i + 'A') + " :: ct :: " + x);
        }
        
        // if more than one elem has same max Freq, add their count in ans,
        // as they need extra one space in the end of queue
        ans += elementsWithMaxFreq - 1;
            
        return Math.max(ans, tasks.length);
    }
}
