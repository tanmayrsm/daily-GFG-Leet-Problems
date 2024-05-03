class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\.");
        int l = 0, r = 0;
            
        while(l < v1.length && r < v2.length) {
            int v1Int = Integer.parseInt(v1[l++]), v2Int = Integer.parseInt(v2[r++]);
            if(v1Int > v2Int)   return 1;
            else if (v1Int < v2Int) return -1;
        }
        while(l < v1.length)
            if(Integer.parseInt(v1[l++]) > 0) return 1;
        
        while(r < v2.length)
            if(Integer.parseInt(v2[r++]) > 0) return -1;
        
        return 0;
    }
}