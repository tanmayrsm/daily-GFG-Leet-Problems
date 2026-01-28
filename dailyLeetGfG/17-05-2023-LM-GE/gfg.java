
class Solution{
    int[][] d = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int isPossible(int n, int m, String s){
        // code here
        char[] x = s.toCharArray();
        int startX = 0;
        int startY = 0;
        
        int xStretchLeft = 0;
        int xStretchRight = 0;
        int xStretch = 0;
        
        int yStretchLeft = 0;
        int yStretchRight = 0;
        int yStretch = 0;
        
        for(char j : x) {
            if(j == 'L')
                startX -= 1;
            else if (j == 'R')
                startX += 1;
            else if (j == 'U')
                startY -= 1;
            else startY += 1;
            
            if(startX < 0) 
                xStretchLeft = Math.min(xStretchLeft, startX);
            if(startX > 0) 
                xStretchRight = Math.max(xStretchRight, startX);
            xStretch = Math.max(xStretch, Math.abs(xStretchLeft) + xStretchRight);
            
            if(startY < 0) 
                yStretchLeft = Math.min(yStretchLeft, startY);
            if(startY > 0) 
                yStretchRight = Math.max(yStretchRight, startY);
            yStretch = Math.max(yStretch, Math.abs(yStretchLeft) + yStretchRight);
            
            if(xStretch >= m || yStretch >= n)
                return 0;
        }
        return 1;
    }
}