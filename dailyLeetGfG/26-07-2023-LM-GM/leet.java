class Solution {
  class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {  // binary search solution
      int l = 1, r = 10000000;
      int ans = Integer.MAX_VALUE;
      while(l <= r) {
        int testSpeed = r + (l - r) / 2;
        if(check(dist, hour, testSpeed)) {
          // it runs for this speed, lets try for lesser speed
          r = testSpeed - 1;
          ans = Math.min(ans, testSpeed);
        } else {
          // it does not runs for testSpeed, lets try with more speed
          l = testSpeed + 1;
        }
      }
      return ans == Integer.MAX_VALUE ? -1 : ans;
  }
  private static boolean check(int[] dist, double hour, int speed) {
    double totalHrs = 0;
    int n = dist.length;
    for(int i = 0; i < n; i++) {
      totalHrs += i != n - 1 ? Math.ceil((double)dist[i] / (double)speed) : (double)dist[i] / (double)speed;
      if(totalHrs > hour)
        return false;
      
    }
    return true;
  }
}
}