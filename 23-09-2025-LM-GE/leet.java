class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n = v1.length, m = v2.length;

        for (int i = 0; i < n || i < m; i++) {
            Integer v1p = (i < n) ? Integer.parseInt(v1[i]) : 0;
            Integer v2p = (j < m) ? Integer.parseInt(v2[i]) : 0;
            if (v1p > v2p)  return 1;
            else if (v1p < v2p) return -1;
        }
        return 0;
    }
}