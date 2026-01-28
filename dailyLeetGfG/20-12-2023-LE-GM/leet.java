class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int metalEnd = -1, glassEnd = -1, paperEnd = -1;
        int metalCt = 0, glassCt = 0, paperCt = 0;
        int ans = 0, n = garbage.length;

        for(int i = 0; i < n; i++) {
            for(char c : garbage[i].toCharArray()) {
                if(c == 'M') {
                    metalEnd = i; metalCt++;
                } else if (c == 'P') {
                    glassEnd = i; glassCt++;
                } else if (c == 'G') {
                    paperEnd = i; paperCt++;
                }
            }
        }
        int currSum = 0;
        for(int i = 0; i < n; i++) {
            if(metalEnd == i) {
                ans += metalCt + currSum;
            }
            if(glassEnd == i) {
                ans += glassCt + currSum;
            }
            if(paperEnd == i) {
                ans += paperCt + currSum;
            }
            if(i < n-1)
                currSum += travel[i];   // kind of making prefix array
            
        }

        return ans;
        
    }
}