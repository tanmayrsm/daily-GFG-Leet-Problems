class Solution {
    public boolean reorderedPowerOf2(int n) {
        char[] num = String.valueOf(n).toCharArray();
        return isPower(0, num);
    }
    private boolean isPower(int curr, char[] num) {
        if (curr == num.length) {
            Integer x = Integer.parseInt(new String(num));
            for (int i = 0; i < num.length; i++)
                if (num[i] == '0')  return false;
                else break;
            return x > 0 && ((x & (x - 1)) == 0);
        }
        boolean res = false;
        for (int i = curr; i < num.length; i++) {
            char temp = num[curr];
            if (i == 0 && num[i] == '0')    continue;
            num[curr] = num[i];
            num[i] = temp;
            res = res | isPower(curr + 1, num);
            if (res)    return res;
            temp = num[i];
            num[i] = num[curr];
            num[curr] = temp;
        }
        return res;
    }
}
// 1,2,4,8,16,32,64,128,256,512,1028,2056,4112


// 215...125
// 
