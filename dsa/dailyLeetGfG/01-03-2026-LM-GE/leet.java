class Solution {
    public int minPartitions(String n) {
        int len = n.length(), maxo = 0;
        for(int i = 0; i < len; i++) {
            maxo = Math.max(maxo, (int)(n.charAt(i) - '0'));
        }
        return maxo;
    }
}