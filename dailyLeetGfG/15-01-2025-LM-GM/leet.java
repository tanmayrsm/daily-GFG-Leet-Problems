class Solution {
    public int minimizeXor(int num1, int num2) {
        String s1String = Integer.toBinaryString(num1);
        char[] s1 = Integer.toBinaryString(num1).toCharArray();
        boolean[] vis = new boolean[s1.length];
        String s2 = Integer.toBinaryString(num2);
        int n = s2.length(), setBits = 0;
        for(int i = 0; i < n; i++)
            if (s2.charAt(i) == '1')
                setBits++;

        for(int i = 0; i < s1.length && setBits > 0; i++) {
            if (s1[i] == '1') {
                vis[i] = true;
                setBits--;
                if (setBits == 0) {
                    for(int j = i + 1; j < s1.length; j++)
                        if (s1[j] == '1')    // this '1' will stay, so in x, it will be '0'
                            s1[j] = '0';
                }
            }
        }
        
        for(int i = s1.length - 1; i >= 0 && setBits > 0; i--) {
            if (s1[i] == '0' && !vis[i]) {
                s1[i] = '1';
                setBits--;
            }
        }
        String res = "1".repeat(setBits) + new String(s1);
        // System.out.println("res ::" + res + " ::" + setBits);
        int no = Integer.parseInt(res, 2);
        return no;
    }
}