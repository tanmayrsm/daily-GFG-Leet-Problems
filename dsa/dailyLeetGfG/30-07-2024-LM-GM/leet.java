class Solution {
    public int minimumDeletions(String s) {
        int bCnt = 0;
        int delReq = 0;
        for(var chr : s.toCharArray()){
            if(chr == 'b')bCnt++;
            else{
                delReq = Math.min(delReq + 1, bCnt);
            }
        }
        return delReq;
    }
}


// aaaabbba
// delReq => aaaa => 0
// delReq => aaaabbb  => 0, bCnt = 3
// delReq => aaaabbba => delReq = min(1, 3) = 1
// aaaabbbaaaa => delReq = min (4,3) = 3




