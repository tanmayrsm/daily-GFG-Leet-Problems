class Solution {
    public long minimumSteps(String s) {
        int n = s.length(), curr = 0;
        long ans = 0L, curr1 = 0L, curr0 = 0L;
        while(curr < n) {
            char currC = s.charAt(curr);
            if(currC == '1') {
                if (curr1 > 0 && curr0 > 0) {   // if both '1' & '0' exists
                    long res = curr1 * curr0;   // update answer
                    ans += res;
                    curr0 = 0;
                    curr1 = 1L + curr1;         // as curr pos is '1' and it has '11...00..(1)' so now string is '00..11(1)', hence add curr 1 to oneCounter variable
                } else
                    curr1++;    // 0 not found before 1, increment 1 counter
                curr++;
            } else if (currC == '0' && curr1 > 0) {
                while(curr < n && s.charAt(curr) == '0') {
                    curr0++;
                    curr++;
                }
            } else curr++;
        }
        if (curr1 > 0 && curr0 > 0)
            ans += (curr1 * curr0);
        return ans;
    }
}