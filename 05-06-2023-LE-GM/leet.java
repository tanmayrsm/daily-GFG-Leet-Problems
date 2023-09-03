class Solution {
    public boolean checkStraightLine(int[][] c) {
        int n = c.length;
        double m = c[1][0] - c[0][0] == 0 ? Double.MAX_VALUE : (double)(c[1][1] - c[0][1]) / (c[1][0] - c[0][0]); 
        for(int i = 2; i < n; i++) {
            double s = c[i][0] - c[i - 1][0] == 0 ?  Double.MAX_VALUE :(double)(c[i][1] - c[i - 1][1]) / (c[i][0] - c[i - 1][0]);
            if(s != m)
                return false;
        }
        return true;
    }
}