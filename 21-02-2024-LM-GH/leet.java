
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int no = right & left;
        char[] noArr = Integer.toBinaryString(no).toCharArray();
        char[] lArr = Integer.toBinaryString(left).toCharArray();
        char[] rArr = Integer.toBinaryString(right).toCharArray();

        for(int i = 0; i < noArr.length; i++) {
            // System.out.print(noArr[i] + "::");
            int rem = noArr.length - i;
            if(noArr[i] == '1') {
                // check if changed bit lies in range, after changing in left no
                lArr[lArr.length - rem] = '0';
                int newInt = Integer.parseInt(new String(lArr), 2);
                if(newInt <= right && newInt >= left)
                    noArr[i] = '0';
                lArr[lArr.length - rem] = '1';

                // check if right changed bit lies in range, after change in right no
                rArr[rArr.length - rem] = '0';
                newInt = Integer.parseInt(new String(rArr), 2);
                if(newInt <= right && newInt >= left)
                    noArr[i] = '0';
                rArr[rArr.length - rem] = '1';
            }
        }

        return Integer.parseInt(new String(noArr), 2);
    }
}
