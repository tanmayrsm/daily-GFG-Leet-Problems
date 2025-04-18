class Solution {
    public String sayN(String n) {
        String res = "";
        int currCt = 1;
        int i = 0;
        while(i < n.length()) {
            if(i+1 < n.length() && n.charAt(i + 1) == n.charAt(i)) {
                currCt++;
                i++;
            } else {
                res += String.valueOf(currCt) + String.valueOf(n.charAt(i));
                currCt = 1;
                i++;
            }
        }
        return res;
    }
    public String countAndSay(int n) {
        String res = "1";
        for(int i = 2; i <= n; i++) {
            res = sayN(res);
        }
        return res;
    }
}